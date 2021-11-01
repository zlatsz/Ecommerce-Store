package sistersart.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "User name is already taken!")
public class UserNameTakenException extends RuntimeException {
    private int statusCode;

    public UserNameTakenException() {
    }
    public UserNameTakenException(String message) {
        super(message);
        this.statusCode = HttpStatus.CONFLICT.value();
    }

    public int getStatusCode() {
        return statusCode;
    }
}
