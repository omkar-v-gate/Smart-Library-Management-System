package j2ee.LibraryManager.exception;


public class ResourceNotFoundException extends RuntimeException{
	
	/**
	 * To avoid warning
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message) {
	
		super(message);
	}
}
