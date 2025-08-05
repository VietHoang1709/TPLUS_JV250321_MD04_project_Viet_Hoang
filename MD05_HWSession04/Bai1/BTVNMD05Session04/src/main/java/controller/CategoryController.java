package controller;

import entity.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CategoryService;
import service.imp.CategoryServiceImp;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/category")
public class CategoryController extends HttpServlet {
    private CategoryService categoryService;

    public CategoryController() {
        categoryService = new CategoryServiceImp();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action.equals("findAll")) {
           findAllCategory(req,resp);
        }else if(action.equals("Update")) {
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            Category category = categoryService.findById(categoryId);
            req.setAttribute("category",category);
            req.getRequestDispatcher("View/updateCategory.jsp").forward(req,resp);
        }else if(action.equals("Update")) {
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            Category category = new Category();
            category.setCategoryId(Integer.parseInt(req.getParameter("categoryId")));
            category.setCategoryName(req.getParameter("categoryName"));
            category.setCategoryDescription(req.getParameter("categoryDescription"));
            category.setStatus(Boolean.parseBoolean(req.getParameter("categoryStatus")));
            boolean result = categoryService.UpdateCategory(category);
            if(result) {
                findAllCategory(req,resp);
            }else{
                req.getRequestDispatcher("View/error.jsp").forward(req,resp);
            }
        }else{
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            boolean result = categoryService.DeleteCategory(categoryId);
            if(result) {
                findAllCategory(req,resp);
            }else{
                req.getRequestDispatcher("View/error.jsp").forward(req,resp);
            }
        }
    }
    public void findAllCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categoryList = categoryService.getCategories();
        // add categoryList vao request
        req.setAttribute("categories", categoryList);
        // Chuyen requset va respone sang View
        req.getRequestDispatcher("/View/listCategory.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if(action.equals("Create")) {
            Category category = new Category();
            category.setCategoryName(req.getParameter("categoryName"));
            category.setCategoryDescription(req.getParameter("categoryDescription"));
            boolean result = categoryService.CreateCategory(category);
            if(result) {
                findAllCategory(req,resp);
            }else{
                req.getRequestDispatcher("/View/error.jsp").forward(req, resp);
            }
        }
    }
}
