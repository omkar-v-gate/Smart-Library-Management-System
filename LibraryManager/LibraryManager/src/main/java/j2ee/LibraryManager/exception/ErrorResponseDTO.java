package j2ee.LibraryManager.exception;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponseDTO {
	
	private int statusCode;
	
	private String message;
	
	private String error;
	
	private String path;
	
	private LocalDateTime timeStamp;
}
