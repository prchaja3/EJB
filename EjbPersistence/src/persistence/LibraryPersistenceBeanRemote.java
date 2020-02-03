package persistence;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface LibraryPersistenceBeanRemote {
	public void addBook(Book book);
	
	public List<Book> getBooks();
}
