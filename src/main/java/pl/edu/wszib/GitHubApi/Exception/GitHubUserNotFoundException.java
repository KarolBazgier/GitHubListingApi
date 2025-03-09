package pl.edu.wszib.GitHubApi.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GitHubUserNotFoundException extends RuntimeException {
    public GitHubUserNotFoundException(String username){
        super("User '" + username + "' not found");
    }
}
