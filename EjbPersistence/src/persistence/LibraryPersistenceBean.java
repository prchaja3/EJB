package persistence;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;

/**
 * Session Bean implementation class LibraryPersistenceBean
 */
@Stateless
@LocalBean
public class LibraryPersistenceBean {

	@PersistenceContext(unitName="EjbPersistence")
	private EntityManager em;
	
	public void addBook(Book book) {
		em.persist(book);
		
	}

	public List<Object> getBooks() {
		return em.createNativeQuery("select * from books").getResultList();
	}

}
