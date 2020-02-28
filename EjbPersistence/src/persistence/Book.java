package persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the books database table.
 * 
 */
@Entity
@Table(name="books", schema="public")
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;


	private Integer id;

	private String name;
	
	private Set<Author> authors;

	public Book() {
	}
	
	public Book(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(name = "book_author", joinColumns = {@JoinColumn(name = "book_id")}, inverseJoinColumns = {@JoinColumn(name = "author_id")})
	public Set<Author> getAuthors(){
		return authors;
	}
}