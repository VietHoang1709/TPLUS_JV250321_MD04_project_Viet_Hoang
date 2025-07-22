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
                products.add(product);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return products;
    }
    @Override
    public boolean CheckProductByProductNameExist(String productName) {
        return false;
    }
    @Override
    public boolean addProduct(Product product) {
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
        return false;
    }

    @Override
    public boolean deleteProduct(Product product) {
        return false;
    }

    @Override
    public List<Product> findProductByName(String productBrand) {
        return List.of();
    }

    @Override
    public List<Product> findProductByPriceRange(float price1, float price2) {
        return List.of();
    }

    @Override
    public List<Product> findProductByStock(int stock, int stock2) {
        return List.of();
    }

    @Override
    public Product findProductById(int id) {
        return null;
    }


}
