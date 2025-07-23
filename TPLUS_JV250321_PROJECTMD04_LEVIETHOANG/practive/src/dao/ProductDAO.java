package dao;

import entity.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> findAllProduct();
    boolean CheckProductByProductNameExist(String productName);
    boolean addProduct(Product product);
    Product findProductById(int id);
    boolean updateProduct(Product product);
    boolean deleteProduct(Product product);
    List<Product> findProductByBrand(String brand);
    List<Product> findProductByPriceRange(float price1, float price2);
    List<Product> findProductByStock(int stock1, int stock2);

}
