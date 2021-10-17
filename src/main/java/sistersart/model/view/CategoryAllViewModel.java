package sistersart.model.view;

import sistersart.model.entity.MainCategory;

public class CategoryAllViewModel {

    private String id;
    private MainCategory mainCategory;
    private String name;

    public CategoryAllViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MainCategory getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(MainCategory mainCategory) {
        this.mainCategory = mainCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
