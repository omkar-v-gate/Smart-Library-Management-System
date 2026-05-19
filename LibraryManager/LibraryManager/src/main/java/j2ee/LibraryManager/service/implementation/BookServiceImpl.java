package j2ee.LibraryManager.service.implementation;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import j2ee.LibraryManager.dto.UpdateBookPriceDTO;
import j2ee.LibraryManager.dto.request.BookRequestDTO;
import j2ee.LibraryManager.dto.response.BookResponseDTO;
import j2ee.LibraryManager.entity.Book;
import j2ee.LibraryManager.exception.ResourceNotFoundException;
import j2ee.LibraryManager.mapper.BookMapper;
import j2ee.LibraryManager.repository.BookRepository;
import j2ee.LibraryManager.service.BookService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
	
	private final BookRepository bookRepository;
	
	private final BookMapper mapper;
	
	@Override
	public BookResponseDTO addBook(BookRequestDTO dto) {
		
		Book book = mapper.toEntity(dto);
		Book newBook = bookRepository.save(book);
		return mapper.toDTO(newBook);
	}
	
	
	@Override
	public BookResponseDTO getBookById(int id) {
		
		Book book = bookRepository.findById(id).orElseThrow(()-> 
		new ResourceNotFoundException("Book not found with given Id: "+ id)); //Throws a custom exception id Id does not exist.
		return mapper.toDTO(book);
	}
	
	@Override
	public List<BookResponseDTO> getAllBooks() {
		
		List<Book> books = bookRepository.findAll();
		return books.stream().map(mapper::toDTO).toList();
	}
	
	@Override
	public BookResponseDTO getBookByTitleAndAuthor(String title, String author) {
		
		Book book = bookRepository.getBookByTitleAndAuthor(title, author);
		return mapper.toDTO(book);
	}
	
	@Override
	public List<BookResponseDTO> getBooksByPrice(double price) {
		
		List<Book> books = bookRepository.getBooksByPrice(price);
		return books.stream().map(mapper::toDTO).toList();
	}
	
	@Override
	public Page<BookResponseDTO> getBooks(Pageable p) {
		
		Page<Book> books = bookRepository.findAll(p);
		return books.map(mapper::toDTO);
	}
	
	@Override
	public BookResponseDTO updateBook(int id, BookRequestDTO dto) {
		
		Book book = bookRepository.findById(id).orElseThrow();
	
		book.setTitle(dto.getTitle());
		book.setAuthor(dto.getAuthor());
		book.setPrice(dto.getPrice());
		book.setYear(dto.getYear());
		
		Book updated = bookRepository.save(book);
		mapper.toDTO(updated);
		return mapper.toDTO(book);
	}

	@Override
	public BookResponseDTO updateBookPrice(int id, UpdateBookPriceDTO dto) {
		
		Book book = bookRepository.findById(id).orElseThrow();
		book.setPrice(dto.getPrice());

		Book b = bookRepository.save(book);
		return mapper.toDTO(b);
	}

	@Override
	public void deleteBook(int id) {
		// bookRepository.deleteById(id);
		
		//OR
		
		Book book = bookRepository.findById(id).orElseThrow();
		bookRepository.delete(book);
	}
}
