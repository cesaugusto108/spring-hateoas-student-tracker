package augusto108.ces.studenttracker.controllers.handlers;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.persistence.NoResultException;
import java.time.LocalDateTime;

@ControllerAdvice
public class RestErrorHandler {
    @ExceptionHandler({NoResultException.class, NoHandlerFoundException.class})
    public ResponseEntity<ErrorResponse> handleNoResultException(Exception e) {
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND, e.getMessage(), e);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(NumberFormatException e) {
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage(), e);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    private static class ErrorResponse {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        private final LocalDateTime timestamp;

        private HttpStatus status;
        private int statusCode;
        private String message;
        private String error;

        private ErrorResponse() {
            this.timestamp = LocalDateTime.now();
        }

        public ErrorResponse(HttpStatus status, String message, Throwable e) {
            this();
            this.status = status;
            this.statusCode = status.value();
            this.message = message;
            this.error = e.toString();
        }

        public HttpStatus getStatus() {
            return status;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public String getMessage() {
            return message;
        }

        public String getError() {
            return error;
        }
    }
}
