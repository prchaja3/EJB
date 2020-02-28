package persistence;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Session Bean implementation class LibraryPersistenceBean
 */
@Stateless
@LocalBean
public class LibraryPersistenceBean {

	@PersistenceContext(unitName="EjbPersistence")
	private EntityManager em;
	
	@Resource
	private SessionContext context;
	
	public void createTimer(long duration, Book book) {
		context.getTimerService().createTimer(duration, book);
	}
	
	@Timeout
	public void timeOutHandler(Timer timer) {
		em.persist(timer.getInfo());
		context.getTimerService().createTimer(60000, new Book("Automatická kniha v " + LocalTime.now().format(DateTimeFormatter.ofPattern("H:mm:ss"))));
	}
	
	public void addBook(Book book) {
		em.persist(book);
		
	}

	public List<Book> getBooks() {
		return em.createNativeQuery("select * from books", Book.class).getResultList();
	}
	
	public void deleteBook(int id) {
		TypedQuery<Book> query = em.createQuery("DELETE FROM Book b WHERE b.id = :id", Book.class);
		query.setParameter("id", id).executeUpdate();
	}

}
