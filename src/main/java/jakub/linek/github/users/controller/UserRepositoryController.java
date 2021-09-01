package jakub.linek.github.users.controller;

import com.fasterxml.jackson.annotation.JsonView;
import jakub.linek.github.users.model.ApiException;
import jakub.linek.github.users.model.User;
import jakub.linek.github.users.model.Views;
import jakub.linek.github.users.service.RepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserRepositoryController {
    private final RepositoryService repositoryService;

    @GetMapping("{login}")
    @JsonView(Views.User.class)
    public User getUserRepositoryInfo(@PathVariable String login) {
        return repositoryService.getUserReposInfo(login);
    }

    @ExceptionHandler({ HttpClientErrorException.class })
    public ResponseEntity<ApiException> handleAll(HttpClientErrorException ex, WebRequest request) {
        ApiException apiException = new ApiException();
        apiException.setMessage(ex.getMessage());

        return new ResponseEntity<ApiException>(
                apiException, new HttpHeaders(), ex.getStatusCode());
    }
}
