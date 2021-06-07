package com.testcreation.trainer.exception;

import org.hibernate.id.IdentifierGenerationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TrainerExceptionController {

		// this will handle all custom exceptions and will return message related to particular exception
		@ExceptionHandler(value = TrainerException.class)
		public ResponseEntity<Object> exception(TrainerException theException){
			return new ResponseEntity<>(theException.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		// if student/trainer give details i.e. already present in database
		// can't create a new account with already existing phone/email 
		@ExceptionHandler(value = DataIntegrityViolationException.class)
		public ResponseEntity<Object> exception(DataIntegrityViolationException e){
			return new ResponseEntity<>("Phone or Email Already exists !",HttpStatus.BAD_REQUEST);
		}
		
		// if primary key is not auto incremented and user give value null
		@ExceptionHandler(value= IdentifierGenerationException.class)
		public ResponseEntity<Object> exception(IdentifierGenerationException e){
			return new ResponseEntity<>("Id can not be null",HttpStatus.BAD_REQUEST);
		}
		
//		// if any of them is null (questionString, answerString, subCategory,score) 
//		@ExceptionHandler(value= NullPointerException.class)
//		public ResponseEntity<Object> exception(NullPointerException e){
//			return new ResponseEntity<>("All four parameters (questionString, answerString, subCategory, score) can't be null",HttpStatus.BAD_REQUEST);
//		}
		
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
		
		// or we can also write this instead of null pointer
		/*@ExceptionHandler(value= IllegalArgumentException.class)
		public ResponseEntity<Object> IllegalArgException(IllegalArgumentException e){
			return new ResponseEntity<>("All four parameters (questionString, answerString, subCategory, score) can't be null",HttpStatus.BAD_REQUEST);
		}*/
		
		

}
