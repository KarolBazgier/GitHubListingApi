package pl.edu.wszib.GitHubApi.Model;

import java.util.List;

public record Response(
        String repositoryName,
        String ownerName,
        List<BranchResponse> branchResponses)

{
}