package sistersart.model.binding;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SearchBindingModel {

    private String search;

    public SearchBindingModel() {
    }

    @NotNull
    @NotEmpty
    public String getSearch() {
        return this.search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
