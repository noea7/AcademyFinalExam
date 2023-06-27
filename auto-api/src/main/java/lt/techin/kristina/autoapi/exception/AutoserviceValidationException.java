package lt.techin.kristina.autoapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AutoserviceValidationException extends RuntimeException{

    public AutoserviceValidationException(String message) {
        super(message);
    }
}
