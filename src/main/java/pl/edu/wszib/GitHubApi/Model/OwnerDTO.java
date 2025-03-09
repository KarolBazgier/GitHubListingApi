package pl.edu.wszib.GitHubApi.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = false)
public class OwnerDTO {
    @JsonProperty("login")
    String login;

    public OwnerDTO(String login) {
        this.login = login;
    }

    public OwnerDTO() {
    }

    public String getLogin() {
        return login;
    }
}
