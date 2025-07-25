package dao.imp;


import dao.ProductDAO;
import entity.Product;
import util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImp implements ProductDAO {

    @Override
    public List<Product> findAllProduct() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> products = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_all_product()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setProductBrand(rs.getString("product_brand"));
                product.setPrice(rs.getFloat("price"));
                product.setStock(rs.getInt("stock"));
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return products;
    }

    @Override
    public boolean CheckProductByProductNameExist(String productName) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call check_exist_product_by_name(?,?)}");
            callSt.setString(1, productName);
            callSt.registerOutParameter(2, java.sql.Types.INTEGER);
            callSt.execute();
            int productId = callSt.getInt(2);
            if (productId == 0) {
                return true; // khong trung
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false; // trung
    }

    @Override
    public boolean addProduct(Product product) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call add_product(?,?,?)}");
            callSt.setString(1, product.getProductName());
            callSt.setString(2, product.getProductBrand());
            callSt.setFloat(3, product.getPrice());
            callSt.executeUpdate();
            return true; // add thanh cong
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false; //  add khong thanh cong
    }
    @Override
    public Product findProductById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Product product = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_product_by_id(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setProductBrand(rs.getString("product_brand"));
                product.setPrice(rs.getFloat("price"));
                product.setStock(rs.getInt("stock"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return product;
    }
    @Override
    public boolean updateProduct(Product product) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_product(?,?,?,?,?)}");
            callSt.setInt(1, product.getProductId());
            callSt.setString(2, product.getProductName());
            callSt.setString(3, product.getProductBrand());
            callSt.setFloat(4, product.getPrice());
            callSt.setInt(5, product.getStock());
            callSt.executeUpdate();
            return true; // Update thanh cong
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false; // update khong thanh cong
    }

    @Override
    public boolean deleteProduct(Product product) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_product(?)}");
            callSt.setInt(1, product.getProductId());
            callSt.executeUpdate();
            return true; // Xoa thanh cong
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false; // Xoa khong thanh cong
    }

    @Override
    public List<Product> findProductByBrand(String brand) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> products = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_product_by_brand(?)}");
            callSt.setString(1, brand);
            ResultSet rs = callSt.executeQuery();
            products = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setProductBrand(rs.getString("product_brand"));
                product.setPrice(rs.getFloat("price"));
                product.setStock(rs.getInt("stock"));
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return products;
    }

    @Override
    public List<Product> findProductByPriceRange(float price1, float price2) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> products = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_product_by_price_range(?,?)}");
            callSt.setFloat(1, price1);
            callSt.setFloat(2, price2);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setProductBrand(rs.getString("product_brand"));
                product.setPrice(rs.getFloat("price"));
                product.setStock(rs.getInt("stock"));
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return products;
    }

    @Override
    public List<Product> findProductByStock(int stock1, int stock2) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> products = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_product_by_stock_range(?,?)}");
            callSt.setInt(1,stock1);
            callSt.setFloat(2,stock2 );
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setProductBrand(rs.getString("product_brand"));
                product.setPrice(rs.getFloat("price"));
                product.setStock(rs.getInt("stock"));
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
       return products;
    }
}
