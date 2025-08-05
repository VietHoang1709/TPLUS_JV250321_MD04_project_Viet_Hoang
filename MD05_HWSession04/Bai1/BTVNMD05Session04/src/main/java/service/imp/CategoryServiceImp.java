package service.imp;

import dao.CategoryDAO;
import dao.imp.CategoryDaoImp;
import entity.Category;
import service.CategoryService;

import java.util.List;

public class CategoryServiceImp implements CategoryService {
    private CategoryDAO categoryDAO;

    public CategoryServiceImp() {
        categoryDAO = new CategoryDaoImp();
    }

    @Override
    public List<Category> getCategories() {
        return categoryDAO.getAllCategories();
    }

    @Override
    public boolean CreateCategory(Category category) {
        return categoryDAO.addCategory(category);
    }

    @Override
    public Category findById(int categoryId) {
        return categoryDAO.getCategoryById(categoryId);
    }

    @Override
    public boolean UpdateCategory(Category category) {
        return categoryDAO.updateCategory(category);
    }

    @Override
    public boolean DeleteCategory(int categoryId) {
        return categoryDAO.deleteCategory(categoryId);
    }
}
