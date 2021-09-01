package jakub.linek.github.users;

import jakub.linek.github.users.model.Follower;
import jakub.linek.github.users.model.Repo;
import jakub.linek.github.users.model.User;
import jakub.linek.github.users.service.RepositoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static jakub.linek.github.users.model.Messages.CANNOT_RETRIEVE_FOLLOWERS_DATA;
import static jakub.linek.github.users.model.Messages.CANNOT_RETRIEVE_USER_DATA;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserRepositoryControllerTest {

    private String LOGIN = "testLogin";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private RepositoryService repositoryService;

    @BeforeEach
    public void setUp() {
        User mockedUser = new User();
        mockedUser.setId(1L);
        mockedUser.setAvatarUrl("avatarUrl");
        mockedUser.setReposUrl("repoUrl");
        mockedUser.setFollowersUrl("followersUrl");
        mockedUser.setCalculations(2.0);
        mockedUser.setNumberOfPublicRepos(1L);
        mockedUser.setNumberOfFollowers(1L);
        mockedUser.setCreatedAt(new Date());
        mockedUser.setName("userName");
        mockedUser.setType("user");
        mockedUser.setLogin(LOGIN);

        Mockito.when(repositoryService.getUserReposInfo(LOGIN)).thenReturn(mockedUser);
    }

    @Test
    public void checkViewMapping(){
        ResponseEntity<User> response = restTemplate.getForEntity("http://localhost:" + port + "/users/"+LOGIN, User.class);

        assert response.getStatusCode() == HttpStatus.OK;

        User user = response.getBody();

        assert user != null;
        assert user.getLogin().equals(LOGIN);
        assert user.getId() != null;
        assert user.getName() != null;
        assert user.getType() != null;
        assert user.getCalculations() != null;
        assert user.getAvatarUrl() != null;
        assert user.getCreatedAt() != null;

        assert user.getNumberOfFollowers() == null;
        assert user.getNumberOfPublicRepos() == null;
        assert user.getFollowersUrl() == null;
        assert user.getReposUrl() == null;
    }
}
