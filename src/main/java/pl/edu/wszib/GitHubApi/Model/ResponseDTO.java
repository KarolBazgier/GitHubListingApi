package pl.edu.wszib.GitHubApi.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDTO {
    private String name;
    @JsonProperty("owner")
    private OwnerDTO owner;
    private List<BranchResponse> branches;
    private boolean fork;

    public ResponseDTO() {
    }

    public ResponseDTO(String name, OwnerDTO ownerName, List<BranchResponse> branches, boolean fork) {
        this.name = name;
        this.owner = ownerName;
        this.branches = branches;
        this.fork = fork;
    }

    public String getName() {
        return name;
    }

    public String getOwnerName() {
        return owner.getLogin();
    }

    public boolean isFork() {
        return fork;
    }
}
