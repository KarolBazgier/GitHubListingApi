package pl.edu.wszib.GitHubApi.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.edu.wszib.GitHubApi.Model.Response;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(GitHubUserNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleUserNotFoundException(GitHubUserNotFoundException e){
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", HttpStatus.NOT_FOUND.value());
        errorResponse.put("message", e.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
