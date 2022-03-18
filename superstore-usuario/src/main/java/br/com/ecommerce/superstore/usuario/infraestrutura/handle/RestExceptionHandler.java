package br.com.ecommerce.superstore.usuario.infraestrutura.handle;

import br.com.ecommerce.superstore.usuario.infraestrutura.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException badRequestException){
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                .details(badRequestException.getMessage())
                .title("Bad Request Exception")
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(badRequestException.getClass().getName())
                .build(), HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<NotFoundExceptionDetails> handlerNotFoundException(NotFoundException notFoundException){
        return new ResponseEntity<>(
                NotFoundExceptionDetails.builder()
                        .details(notFoundException.getMessage())
                        .title("Not Found Exception")
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .message(notFoundException.getClass().getName())
                        .build(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ConflictException.class)
     ResponseEntity<ConflictExceptionDetails> handlerConflictException(ConflictException conflictException){
        return new ResponseEntity<>(
                ConflictExceptionDetails.builder()
                        .details(conflictException.getMessage())
                        .title("Not Found Exception")
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.CONFLICT.value())
                        .message(conflictException.getClass().getName())
                        .build(), HttpStatus.CONFLICT);
    }
}
