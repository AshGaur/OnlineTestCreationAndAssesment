package com.testcreation.trainer.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.id.IdentifierGenerationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonParseException;

@ControllerAdvice
public class TrainerExceptionController {

		// this will handle all custom exceptions and will return message related to particular exception
		@ExceptionHandler(value = TrainerException.class)
		public ResponseEntity<Object> exception(TrainerException theException){
			return new ResponseEntity<>(theException.getMessage(),HttpStatus.BAD_REQUEST);//400 badrequest
		}
		
		@ExceptionHandler(value = ConstraintViolationException.class)
		public ResponseEntity<Object> exception( ConstraintViolationException e){
			return new ResponseEntity<>("Constraint not followed :"+e.getSQLException(),HttpStatus.BAD_REQUEST);
		}
		
		// Validation of fields 
		@ExceptionHandler(value = ValidationException.class)
		public ResponseEntity<Object> exception(ValidationException e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		// Validation of fields 
		@ExceptionHandler(value = TestException.class)
		public ResponseEntity<Object> exception(TestException e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		// Validation of fields 
		@ExceptionHandler(value = JsonParseException.class)
		public ResponseEntity<Object> exception(JsonParseException e){
			return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}

}
