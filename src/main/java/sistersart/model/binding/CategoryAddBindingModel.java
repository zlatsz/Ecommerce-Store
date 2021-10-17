package sistersart.model.binding;

import org.hibernate.validator.constraints.Length;
import sistersart.model.entity.MainCategory;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoryAddBindingModel {

    private MainCategory mainCategory;
    private String name;

    public CategoryAddBindingModel() {
    }

    @Enumerated(value = EnumType.STRING)
    public MainCategory getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(MainCategory mainCategory) {
        this.mainCategory = mainCategory;
    }

    @NotNull
    @NotEmpty
    @Length(min = 3, max = 20, message = "Category must be between 3 and 20 character")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
