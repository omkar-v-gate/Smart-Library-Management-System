package j2ee.LibraryManager.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import j2ee.LibraryManager.dto.request.BookRequestDTO;
import j2ee.LibraryManager.dto.response.BookResponseDTO;
import j2ee.LibraryManager.entity.Book;

@Component
public class BookMapper {
	
	@Autowired
	private ModelMapper mapper;
	
	public BookResponseDTO toDTO(Book book) {
		
		return mapper.map(book, BookResponseDTO.class);
	}
	public Book toEntity(BookRequestDTO dto) {
		
		return mapper.map(dto, Book.class);
	}
}
