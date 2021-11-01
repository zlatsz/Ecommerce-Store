package sistersart.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Product already exists.")
public class ProductAlreadyExistsException extends RuntimeException {

    private int statusCode;

    public ProductAlreadyExistsException() {

    }

    public ProductAlreadyExistsException(String message) {
        super(message);
        this.statusCode = HttpStatus.CONFLICT.value();
    }

    public int getStatusCode() {
        return statusCode;
    }

}
