package business;

import entity.Product;

import java.util.List;

public interface ProductBusiness {
    List<Product> findAllProducts();
}
