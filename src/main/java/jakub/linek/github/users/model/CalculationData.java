package jakub.linek.github.users.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class CalculationData {
    private Long numberOfFollowers;
    private Long NumberOfPublicRepos;

    @JsonProperty("followers_url")
    private String followersUrl;

    @JsonProperty("repos_url")
    private String reposUrl;

}
