package in.sp.tailor.exception; // Adjust package to match your project

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // This catches ANY RuntimeException thrown in your app
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        
        // Create a clean JSON response
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("status", "Error");
        
        // You can customize these messages later for specific MySQL errors (like Duplicate Key)
        errorResponse.put("message", ex.getMessage());

        // Return it with a 400 Bad Request status instead of crashing
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}