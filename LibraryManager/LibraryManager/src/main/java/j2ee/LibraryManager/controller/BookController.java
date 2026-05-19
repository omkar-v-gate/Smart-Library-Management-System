package j2ee.LibraryManager.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import j2ee.LibraryManager.dto.UpdateBookPriceDTO;
import j2ee.LibraryManager.dto.request.BookRequestDTO;
import j2ee.LibraryManager.dto.response.ApiResponseDTO;
import j2ee.LibraryManager.dto.response.BookResponseDTO;
import j2ee.LibraryManager.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("/book")
public class BookController {
	
	private BookService bookService;
	
	public BookController(BookService bookService) 
	{
		this.bookService = bookService;
	}
	
	@PostMapping
	public ResponseEntity<ApiResponseDTO<BookResponseDTO>> addBook(@Valid @RequestBody BookRequestDTO bookDTO) {
		
		BookResponseDTO book = bookService.addBook(bookDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponseDTO.created(book, "Book added successfully!"));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponseDTO<BookResponseDTO>> getBook(@PathVariable int id) {
		
		BookResponseDTO book = bookService.getBookById(id);
		
		return ResponseEntity.ok(ApiResponseDTO.success(book, "Book fetched successfully!"));
	}
	
	@GetMapping
	public ResponseEntity<ApiResponseDTO<List<BookResponseDTO>>> getAllBook(){
		
		List<BookResponseDTO> book = bookService.getAllBooks();
		
		return ResponseEntity.ok(ApiResponseDTO.success(book, "All books fetched successfully!"));
	}
	
	@GetMapping("/search")
	public ResponseEntity<ApiResponseDTO<BookResponseDTO>> getBookByTitleAndAuthor(@RequestParam @NotBlank (message = "title is required") String title, @RequestParam @NotBlank (message = "author is required") String author){
		
		BookResponseDTO book = bookService.getBookByTitleAndAuthor(title, author);
		
		return ResponseEntity.ok(ApiResponseDTO.success(book, "Books by Title and Author fetched successfully!"));
	}
	
	@GetMapping("/price")
	public ResponseEntity<ApiResponseDTO<List<BookResponseDTO>>> getBooksByPrice(@RequestParam @Positive(message = "price must be greater than 0") double price){
		
		List<BookResponseDTO> books = bookService.getBooksByPrice(price);
		
		return ResponseEntity.ok(ApiResponseDTO.success(books, "Books with given price fetched successfully!"));
	}
	
	@GetMapping("/filter")
	public ResponseEntity<ApiResponseDTO<List<BookResponseDTO>>> getBooks(@RequestParam(defaultValue = "0") int page, @RequestParam (defaultValue = "2") int size, @RequestParam (defaultValue = "title") String sortBy, @RequestParam (defaultValue = "asc") String sortDirection){
		
		//Sorting
		
		Sort sort = (sortDirection.equalsIgnoreCase("desc")) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
		
		Pageable p = PageRequest.of(page, size, sort);
		
		Page<BookResponseDTO> books = bookService.getBooks(p);
		
		return ResponseEntity.ok(ApiResponseDTO.success(books.toList(), "Books fetched successfully!"));
	}
	
	@PutMapping("/{id}")  
	public ResponseEntity<ApiResponseDTO<BookResponseDTO>> updateBook(@PathVariable int id, @Valid @RequestBody BookRequestDTO dto) {
		
		BookResponseDTO book = bookService.updateBook(id, dto);
		
		return ResponseEntity.ok(ApiResponseDTO.success(book, "Book updated successfully!"));
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<ApiResponseDTO<BookResponseDTO>>updateBookPrice(@PathVariable int id, @Valid @RequestBody UpdateBookPriceDTO dto, @RequestHeader(value ="Prefer", required = false) String prefer) {
		
		BookResponseDTO book = bookService.updateBookPrice(id, dto);
		
		if("return = representation".equals(prefer)) {
			
			return ResponseEntity.ok(ApiResponseDTO.success(book, "Price updated successfully!"));
		}
		
		return ResponseEntity.noContent().build();
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable int id) {
		
		bookService.deleteBook(id);
		
		return ResponseEntity.noContent().build();
	}
}