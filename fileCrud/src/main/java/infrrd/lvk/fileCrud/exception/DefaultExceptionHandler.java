package infrrd.lvk.fileCrud.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import infrrd.lvk.fileCrud.model.ErrorMessage;

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8891959764862530715L;

	@ExceptionHandler(FileCRUDException.class )
	public ResponseEntity<ErrorMessage> handleFileNotFoundException(FileCRUDException ex,WebRequest webRequest){
		 // create payload containing 	exception details
		ErrorMessage errMsg=new ErrorMessage(ex.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());
		 //return response entry
		 
		 return new ResponseEntity<ErrorMessage>(errMsg,new HttpHeaders(), HttpStatus.BAD_REQUEST);
		 
	 }
}