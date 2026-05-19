package j2ee.LibraryManager.dto.response;

import org.springframework.http.HttpStatus;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApiResponseDTO<T> { //<T> Represents a generic of any Non-Primitive data type
	
	private int status;
	
	private String message;
	
	private T data;
	
	public static <T> ApiResponseDTO<T> success(T data, String message){ //Factory Method
		
		return ApiResponseDTO.<T>builder().status(HttpStatus.OK.value()).message(message).data(data).build();
	
	}
	
	public static <T> ApiResponseDTO<T> created(T data, String message){ //Factory Method
		
		return ApiResponseDTO.<T>builder().status(HttpStatus.CREATED.value()).message(message).data(data).build();
	
	}
}