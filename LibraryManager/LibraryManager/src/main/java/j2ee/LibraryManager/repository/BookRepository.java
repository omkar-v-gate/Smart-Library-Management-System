package j2ee.LibraryManager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import j2ee.LibraryManager.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	
	public Book getBookByTitleAndAuthor(String title, String author);
	//Select b from Book b
	//Where b.title =:title and b.author =:author //We can pass this JPQL query if using @Query annotation above this method
	
	@Query("Select b from Book b where b.price =:price")
	public List<Book> getBooksByPrice(double price);
}