package pl.edu.wszib.GitHubApi.Service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import pl.edu.wszib.GitHubApi.Exception.GitHubUserNotFoundException;
import pl.edu.wszib.GitHubApi.Model.BranchResponse;
import pl.edu.wszib.GitHubApi.Model.Response;
import pl.edu.wszib.GitHubApi.Model.ResponseDTO;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class GitHubService {
    private final WebClient webClient;

    public GitHubService(WebClient webClient) {
        this.webClient = webClient;
    }


    public Mono<List<Response>> getRepositories(String username) {
        return webClient.get()
                .uri("/users/{username}/repos", username)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> {
                    if (response.statusCode().equals(HttpStatus.NOT_FOUND))
                        return Mono.error(new GitHubUserNotFoundException(username));
                    else
                        return Mono.error(new RuntimeException("unexpexceted error"));

                })
                .bodyToFlux(ResponseDTO.class)

                .filter(repo -> !repo.isFork())

                .flatMap(repo -> fetchBranches(username, repo.getName())
                        .map(branches -> new Response(repo.getName(), repo.getOwnerName(), branches)))
                .collectList();
    }

    private Mono<List<BranchResponse>> fetchBranches(String username, String repoName) {
        return webClient.get()
                .uri("/repos/{username}/{repo}/branches", username, repoName)
                .retrieve()
                .bodyToFlux(BranchResponse.class)
                .collectList();
    }
}
