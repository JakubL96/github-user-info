package jakub.linek.github.users;

import jakub.linek.github.users.dao.StatisticRepo;
import jakub.linek.github.users.model.Follower;
import jakub.linek.github.users.model.Repo;
import jakub.linek.github.users.model.User;
import jakub.linek.github.users.service.CalculateService;
import jakub.linek.github.users.service.RepositoryService;
import jakub.linek.github.users.service.RequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;

import static jakub.linek.github.users.model.Messages.CANNOT_RETRIEVE_FOLLOWERS_DATA;
import static jakub.linek.github.users.model.Messages.CANNOT_RETRIEVE_USER_DATA;

@SpringBootTest
public class RepositoryServiceTest {
    private String LOGIN = "testUser";
    private String REPO_URL = "repoUrl";
    private String FOLLOWER_URL = "followerUrl";

    @MockBean
    private CalculateService calculateService;

    @MockBean
    private RequestService requestService;

    @Autowired
    private RepositoryService repositoryService;

    @Value("${repository.url:https://api.github.com/users/{login}}")
    private String REPOSITORY_URL;

    @BeforeEach
    public void setUp() {
        User mockedUser = new User();
        mockedUser.setId(1L);
        mockedUser.setAvatarUrl("avatarUrl");
        mockedUser.setReposUrl(REPO_URL);
        mockedUser.setFollowersUrl(FOLLOWER_URL);
        mockedUser.setCreatedAt(new Date());
        mockedUser.setName("userName");
        mockedUser.setType("user");

        Mockito.when(requestService.makeGetCall(REPOSITORY_URL, User.class, CANNOT_RETRIEVE_USER_DATA, LOGIN))
                .thenReturn(mockedUser);

        Mockito.when(requestService.makeGetCall(mockedUser.getFollowersUrl(), Follower[].class, CANNOT_RETRIEVE_FOLLOWERS_DATA))
                .thenReturn(new Follower[]{
                        new Follower(),
                        new Follower(),
                        new Follower(),
                        new Follower(),
                        new Follower()
                });

        Mockito.when(requestService.makeGetCall(mockedUser.getReposUrl(), Repo[].class, CANNOT_RETRIEVE_FOLLOWERS_DATA))
                .thenReturn(new Repo[]{
                        new Repo(true),
                        new Repo(false),
                        new Repo(false),
                        new Repo(true)
                });

        Mockito.when(calculateService.calculate(5L, 2L)).thenReturn(1.0);
    }

    @Test
    public void shouldReturnFullRepositoryInfo(){
        User user = repositoryService.getUserReposInfo(LOGIN);

        assert user.getNumberOfFollowers() == 5;
        assert user.getNumberOfPublicRepos() == 2;
        assert user.getCalculations() == 1.0;
    }

}
