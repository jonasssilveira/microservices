package produtos.handle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import produtos.exceptions.BadRequestException;
import produtos.exceptions.BadRequestExceptionDetails;
import produtos.exceptions.NotFoundException;
import produtos.exceptions.NotFoundExceptionDetails;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException badRequestException) {
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
    ResponseEntity<NotFoundExceptionDetails> handlerNotFoundException(NotFoundException notFoundException) {
        return new ResponseEntity<>(
                NotFoundExceptionDetails.builder()
                        .details(notFoundException.getMessage())
                        .title("Not Found Exception")
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .message(notFoundException.getClass().getName())
                        .build(), HttpStatus.NOT_FOUND);
    }
}
