package sistersart.error;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Product not found.")
public class ProductNotFoundException extends RuntimeException {

    private int statusCode;

    public ProductNotFoundException() {

    }

    public ProductNotFoundException(String message) {
        super(message);
        this.statusCode = HttpStatus.NOT_FOUND.value();
    }

    public int getStatusCode() {
        return statusCode;
    }
}
