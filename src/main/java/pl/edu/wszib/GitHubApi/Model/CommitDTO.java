package pl.edu.wszib.GitHubApi.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommitDTO {
    @JsonProperty("sha")
    private String lastCommitSha;

    public CommitDTO(String lastCommitSha) {
        this.lastCommitSha = lastCommitSha;
    }

    public CommitDTO() {
    }

    public String getLastCommitSha() {
        return lastCommitSha;
    }
}

