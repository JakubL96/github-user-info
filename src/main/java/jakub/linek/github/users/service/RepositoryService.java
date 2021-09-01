package jakub.linek.github.users.service;

import jakub.linek.github.users.dao.StatisticRepo;
import jakub.linek.github.users.exception.NoDataException;
import jakub.linek.github.users.model.Follower;
import jakub.linek.github.users.model.Repo;
import jakub.linek.github.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static jakub.linek.github.users.model.Messages.*;

@Service
@RequiredArgsConstructor
public class RepositoryService {
    private final CalculateService calculateService;
    private final StatisticRepo statisticRepo;
    private final RequestService requestService;

    @Value("${repository.url:https://api.github.com/users/{login}}")
    private String REPOSITORY_URL;

    public User getUserReposInfo(String login) {
        statisticRepo.incrementCounter(login);

        User user = getUserInfo(login);

        getFollowers(user);
        getPublicRepos(user);
        addCalculates(user);

        return  user;
    }

    private User getUserInfo(String login) {
        return requestService.makeGetCall(REPOSITORY_URL, User.class, CANNOT_RETRIEVE_USER_DATA, login);
    }

    private void getFollowers(User user) {
        Follower[] followers = requestService.makeGetCall(user.getFollowersUrl(), Follower[].class, CANNOT_RETRIEVE_FOLLOWERS_DATA);
        user.setNumberOfFollowers((long) followers.length);
    }

    private void getPublicRepos(User user) {
        Repo[] repos = requestService.makeGetCall(user.getReposUrl(), Repo[].class, CANNOT_RETRIEVE_FOLLOWERS_DATA);

        user.setNumberOfPublicRepos(
                Arrays.stream(repos).filter(repo->!repo.getIsPrivate()).count()
        );
    }

    private void addCalculates(User user) {
        user.setCalculations(
                calculateService.calculate(user.getNumberOfFollowers(), user.getNumberOfPublicRepos())
        );
    }

}
