package sistersart.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "User was not created")
public class UserWrongCredentialsException extends RuntimeException {
    private int statusCode;

    public UserWrongCredentialsException() {
    }
    public UserWrongCredentialsException(String message) {
        super(message);
        this.statusCode = HttpStatus.BAD_REQUEST.value();
    }

    public int getStatusCode() {
        return statusCode;
    }
}
