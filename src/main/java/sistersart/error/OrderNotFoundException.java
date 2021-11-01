package sistersart.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Order not found.")
public class OrderNotFoundException extends RuntimeException {
    private int statusCode;

    public OrderNotFoundException() {

    }

    public OrderNotFoundException(String message) {
        super(message);
        this.statusCode = HttpStatus.NOT_FOUND.value();
    }

    public int getStatusCode() {
        return statusCode;
    }
}
