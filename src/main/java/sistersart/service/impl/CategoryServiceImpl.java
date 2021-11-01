package sistersart.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sistersart.error.CategoryNotFoundException;
import sistersart.model.entity.Category;
import sistersart.model.service.CategoryServiceModel;
import sistersart.repository.CategoryRepository;
import sistersart.service.CategoryService;
import sistersart.validation.CategoryValidation;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private CategoryValidation categoryValidation;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper, CategoryValidation categoryValidation) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.categoryValidation = categoryValidation;
    }


    @Override
    public CategoryServiceModel addCategory(CategoryServiceModel categoryServiceModel) {
        if (!categoryValidation.isValid(categoryServiceModel)){
            throw new CategoryNotFoundException("Category not found");
        }
        Category category = this.modelMapper.map(categoryServiceModel, Category.class);
        return this.modelMapper
                .map(this.categoryRepository.saveAndFlush(category), CategoryServiceModel.class);
    }

    @Override
    public List<CategoryServiceModel> findAllCategories() {
        return this.categoryRepository.findAll()
                .stream()
                .map(c -> this.modelMapper.map(c, CategoryServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryServiceModel findCategoryById(String id) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);
        return this.modelMapper.map(category, CategoryServiceModel.class);
    }

    @Override
    public CategoryServiceModel editCategory(String id, CategoryServiceModel categoryServiceModel) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);
        category.setName(categoryServiceModel.getName());
        return this.modelMapper.map(this.categoryRepository.saveAndFlush(category), CategoryServiceModel.class);
    }

    @Override
    public CategoryServiceModel deleteCategory(String id) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        this.categoryRepository.delete(category);
        return this.modelMapper.map(category, CategoryServiceModel.class);
    }

    @Override
    public List<CategoryServiceModel> getAll() {
        return this.categoryRepository.findAll().stream().map(c ->
                this.modelMapper.map(c, CategoryServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryServiceModel> findCategoryByName(String name) {

        return this.categoryRepository.findAll().stream()
                .filter(c->c.getName().equals(name))
                .map(c -> this.modelMapper.map(c, CategoryServiceModel.class))
                .collect(Collectors.toList());
    }
}
