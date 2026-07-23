package server.exception;

// import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
                String message = ex.getBindingResult()
                                .getFieldErrors()
                                .get(0)
                                .getDefaultMessage();

                return ResponseEntity.badRequest().body(Map.of(
                                "success", false,
                                "message", message));
        }

        @ExceptionHandler(CustomException.DuplicateEmailException.class)
        public ResponseEntity<Map<String, Object>> handleDuplicateEmail(CustomException.DuplicateEmailException ex) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                                "success", false,
                                "message", ex.getMessage()));
        }

        @ExceptionHandler(CustomException.NotFoundException.class)
        public ResponseEntity<Map<String, Object>> handleNotFound(CustomException.NotFoundException ex) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                                "success", false,
                                "message", ex.getMessage()));
        }

        @ExceptionHandler(CustomException.InvalidPasswordException.class)
        public ResponseEntity<Map<String, Object>> handleInvalidPassword(CustomException.InvalidPasswordException ex) {
                return ResponseEntity.badRequest().body(Map.of(
                                "success", false,
                                "message", ex.getMessage()));
        }

        @ExceptionHandler(CustomException.SamePasswordException.class)
        public ResponseEntity<Map<String, Object>> handleSamePassword(CustomException.SamePasswordException ex) {
                return ResponseEntity.badRequest().body(Map.of(
                                "success", false,
                                "message", ex.getMessage()));
        }

        @ExceptionHandler(CustomException.UnauthorizedException.class)
        public ResponseEntity<Map<String, Object>> handleUnauthorized(CustomException.UnauthorizedException ex) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                                "success", false,
                                "message", ex.getMessage()));
        }
}