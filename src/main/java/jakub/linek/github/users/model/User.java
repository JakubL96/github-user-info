package jakub.linek.github.users.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends CalculationData{
    @JsonView(Views.User.class)
    private String login;

    @JsonView(Views.User.class)
    private Long id;

    @JsonView(Views.User.class)
    private String name;

    @JsonView(Views.User.class)
    private String type;

    @JsonView(Views.User.class)
    private Double calculations;

    @JsonView(Views.User.class)
    @JsonProperty("avatar_url")
    private String avatarUrl;

    @JsonView(Views.User.class)
    @JsonProperty("created_at")
    private Date createdAt;
}
