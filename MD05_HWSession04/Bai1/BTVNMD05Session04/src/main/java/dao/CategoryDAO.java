package dao;

import entity.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> getAllCategories();
    boolean addCategory(Category category);
    Category getCategoryById(int id);
    boolean updateCategory(Category category);
    boolean deleteCategory(int id);
}
