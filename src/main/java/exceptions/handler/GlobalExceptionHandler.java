package exceptions.handler;

import exceptions.UserNotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.Locale;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse handleUserNotFound(
            UserNotFoundException ex, WebRequest request, Locale locale) {

        String message = messageSource.getMessage(
                ex.getMessage(),
                ex.getArgs(),
                "user.not.found",
                locale);

        return ErrorResponse.builder(ex, HttpStatus.NOT_FOUND, message)
                .title("User not found")
                .property("timestamp", Instant.now())
                .property("path", request.getDescription(false).replace("uri=", ""))
                .build();
    }
}