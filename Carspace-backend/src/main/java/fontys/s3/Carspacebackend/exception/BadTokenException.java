package fontys.s3.Carspacebackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BadTokenException extends ResponseStatusException {
    public BadTokenException(String message){
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
