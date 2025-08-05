package service;

import entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();
    boolean CreateCategory(Category category);
    Category findById(int categoryId);
    boolean UpdateCategory(Category category);
    boolean DeleteCategory(int categoryId);

}
