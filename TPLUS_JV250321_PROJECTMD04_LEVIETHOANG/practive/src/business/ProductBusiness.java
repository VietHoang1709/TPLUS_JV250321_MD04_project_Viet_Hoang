package business;

import entity.Product;

import java.util.List;

public interface ProductBusiness {
    List<Product> findAllProducts();
    boolean checkExistProduct(String productName);
    boolean addProduct(Product product);
    Product findProductById(int productId);
    boolean updateProduct(Product product);
    boolean deleteProduct(Product product);
    List<Product> findAllProductsByBrand(String brand);
    List<Product> findProductByPrice(float price1, float price2);
    List<Product> findProductByStockRange(int stock1, int stock2);
}
