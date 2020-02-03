package persistence;

import java.io.Serializable;
import javax.persistence.*;


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

	public Book() {
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

	@Column(name="name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

}