package infrrd.lvk.fileCrud.exception;

public class FileCRUDException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6261225294190133455L;

	public FileCRUDException(String message) {
		super(message);
	}

	public FileCRUDException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
