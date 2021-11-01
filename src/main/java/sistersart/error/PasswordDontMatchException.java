package sistersart.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Incorrect password")
public class PasswordDontMatchException extends RuntimeException {

    private int statusCode;

    public PasswordDontMatchException() {

    }

    public PasswordDontMatchException(String message) {
        super(message);
        this.statusCode = HttpStatus.UNAUTHORIZED.value();
    }

    public int getStatusCode() {
        return statusCode;
    }
}
