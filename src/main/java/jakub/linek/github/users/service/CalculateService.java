package jakub.linek.github.users.service;

import org.springframework.stereotype.Service;

@Service
public class CalculateService {

    public Double calculate(Long numberOfFollowers, Long numberOfPublicRepos){
        if(numberOfFollowers == 0) return Double.NaN;

        return (6.0D / numberOfFollowers) * (2.0D + numberOfPublicRepos);
    }
}
