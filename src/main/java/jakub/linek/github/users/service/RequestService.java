package jakub.linek.github.users.service;

import jakub.linek.github.users.exception.NoDataException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RequestService {
    private final RestTemplate restTemplate;

    public RequestService() {
        this.restTemplate = new RestTemplate();
    }


    public <T> T makeGetCall(String url, Class<T> responseType, String defaultErrorMessage, Object... args){
        ResponseEntity<T> response = this.restTemplate.getForEntity(url, responseType, args);

        if(response.getBody() == null) throw new NoDataException(HttpStatus.NOT_FOUND, defaultErrorMessage);

        return response.getBody();
    }
}
