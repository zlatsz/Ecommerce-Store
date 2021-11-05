package sistersart.model.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserLoginBindingModel {

    private String username;
    private String password;
    private String email;

    @NotNull
    @NotEmpty
    @Length(min = 3, max = 15, message = "Username must be between 3 and 15 character")
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull
    @NotEmpty
    @Length(min = 4, max = 20, message = "Password must be between 4 and 20 character")
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Email
    @NotEmpty(message = "Enter valid email")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
