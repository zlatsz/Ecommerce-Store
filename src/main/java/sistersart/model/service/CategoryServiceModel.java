package sistersart.model.service;

import sistersart.model.entity.MainCategory;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoryServiceModel extends BaseServiceModel {

    private MainCategory mainCategory;
    private String name;

    public CategoryServiceModel() {
    }

    public MainCategory getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(MainCategory mainCategory) {
        this.mainCategory = mainCategory;
    }

    @NotNull
    @NotEmpty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
