package jakub.linek.github.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.charset.Charset;

public class NoDataException extends HttpClientErrorException {

    public NoDataException(HttpStatus statusCode) {
        super(statusCode);
    }

    public NoDataException(HttpStatus statusCode, String statusText) {
        super(statusCode, statusText);
    }

    public NoDataException(HttpStatus statusCode, String statusText, byte[] body, Charset responseCharset) {
        super(statusCode, statusText, body, responseCharset);
    }
}
