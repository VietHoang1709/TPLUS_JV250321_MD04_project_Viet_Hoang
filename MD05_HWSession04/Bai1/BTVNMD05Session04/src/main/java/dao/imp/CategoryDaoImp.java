package dao.imp;

import dao.CategoryDAO;
import entity.Category;
import util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImp implements CategoryDAO {


    @Override
    public List<Category> getAllCategories() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Category> categoryList = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL display_all_category()}");
            ResultSet rs =  callSt.executeQuery();
            categoryList = new ArrayList<>();
            while(rs.next()){
                Category category = new Category();
                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setCategoryDescription(rs.getString("category_description"));
                category.setStatus(rs.getBoolean("status"));
                categoryList.add(category);
                System.out.println("Số lượng danh mục: " + categoryList.size());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt );
        }
        return categoryList;
    }

    @Override
    public boolean addCategory(Category category) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL create_category(?,?)}");
            callSt.setString(1, category.getCategoryName());
            callSt.setString(2,category.getCategoryDescription());
            callSt.executeUpdate();
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;
    }

    @Override
    public Category getCategoryById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Category category = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL find_category_by_id(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if(rs.next()){
                category = new Category();
                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                category.setCategoryDescription(rs.getString("category_description"));
                category.setStatus(rs.getBoolean("status"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt );
        }
        return category;
    }

    @Override
    public boolean updateCategory(Category category) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL update_category(?,?,?,?)}");
            callSt.setInt(1, category.getCategoryId());
            callSt.setString(2, category.getCategoryName());
            callSt.setString(3,category.getCategoryDescription());
            callSt.setBoolean(4,category.isStatus());
            callSt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt );
        }
        return result;
    }

    @Override
    public boolean deleteCategory(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL delete_category(?)}");
            callSt.setInt(1, id);
            callSt.executeUpdate();
            result = true;
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt );
        }
        return result;
    }
}
