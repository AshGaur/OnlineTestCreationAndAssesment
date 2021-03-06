package com.testcreation.students.exception;


import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class StudentExceptionController {
	
	@ExceptionHandler(value = ValidationException.class)
	public ResponseEntity<Object> exception(ValidationException theException){
		return new ResponseEntity<>(theException.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<Object> exception( ConstraintViolationException e){
		return new ResponseEntity<>("Constraint not followed :"+e.getSQLException(),HttpStatus.BAD_REQUEST);
	}
	
//	@ExceptionHandler(value = DataIntegrityViolationException.class)
//	public ResponseEntity<Object> exception(DataIntegrityViolationException theException){
//		return new ResponseEntity<>("Constraint Not Followed :"+theException.getMessage(),HttpStatus.BAD_REQUEST);
//	}

	@ExceptionHandler(value = StudentException.class)
	public ResponseEntity<Object> exception(StudentException e){
		return new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = AttemptException.class)
	public ResponseEntity<Object> exception(AttemptException e){
		return new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
