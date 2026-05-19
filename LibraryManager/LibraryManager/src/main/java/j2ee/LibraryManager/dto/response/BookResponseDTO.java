package j2ee.LibraryManager.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookResponseDTO {
	
	private int id;
	
	private String title;
	
	private String author;
	
	private double price;
	
	private int year;
}
