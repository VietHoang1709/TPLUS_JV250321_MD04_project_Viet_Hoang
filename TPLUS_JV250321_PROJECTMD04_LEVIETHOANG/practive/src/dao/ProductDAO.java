package dao;

import entity.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> findAllProduct();
    boolean addProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(Product product);
    List<Product> findProductByName(String productBrand);
    List<Product> findProductByPriceRange(float price1, float price2);
    List<Product> findProductByStock(int stock, int stock2);
    Product findProductById(int id);
    boolean CheckProductByProductNameExist(String productName);
}
