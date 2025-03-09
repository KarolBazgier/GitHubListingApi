package pl.edu.wszib.GitHubApi.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wszib.GitHubApi.Model.Response;
import pl.edu.wszib.GitHubApi.Service.GitHubService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@CrossOrigin("*")
public class GitHubController {
    private  final GitHubService gitHubService;

    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/{username}")
    public Flux<Response> getRepositories(@PathVariable String username){
        return gitHubService.getRepositories(username).flatMapMany(Flux::fromIterable);
    }
}
