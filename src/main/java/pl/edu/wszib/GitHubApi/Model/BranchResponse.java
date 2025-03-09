package pl.edu.wszib.GitHubApi.Model;

public record BranchResponse (
        String name,
        CommitDTO commit ) {
}
