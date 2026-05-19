package j2ee.LibraryManager.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import j2ee.LibraryManager.dto.UpdateBookPriceDTO;
import j2ee.LibraryManager.dto.request.BookRequestDTO;
import j2ee.LibraryManager.dto.response.BookResponseDTO;

public interface BookService {
	
	public BookResponseDTO addBook(BookRequestDTO dto);
	
	public BookResponseDTO getBookById(int id);
	
	public List<BookResponseDTO> getAllBooks();
	
	public BookResponseDTO getBookByTitleAndAuthor(String title, String author);
	
	public List<BookResponseDTO> getBooksByPrice(double price);
	
	public Page<BookResponseDTO> getBooks(Pageable p); //Pagination
	
	public BookResponseDTO updateBook(int id, BookRequestDTO dto);
	
	public BookResponseDTO updateBookPrice(int id, UpdateBookPriceDTO dto);
	
	public void deleteBook(int id);
	
}
