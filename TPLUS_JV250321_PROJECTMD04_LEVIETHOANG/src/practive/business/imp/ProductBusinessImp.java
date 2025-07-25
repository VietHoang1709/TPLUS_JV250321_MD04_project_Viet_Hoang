package business.imp;

import business.ProductBusiness;
import dao.ProductDAO;
import dao.imp.ProductDaoImp;
import entity.Product;

import java.util.List;

public class ProductBusinessImp implements ProductBusiness {
    private final ProductDAO productDAO;

    public ProductBusinessImp() {
        productDAO = new ProductDaoImp();
    }
    // Thêm mới sản phẩm
    @Override
    public List<Product> findAllProducts() {
        return productDAO.findAllProduct();
    }
    // Kiểm tra sự tồn tại của tên sản phẩm
    @Override
    public boolean checkExistProduct(String productName) {
        return productDAO.CheckProductByProductNameExist(productName);
    }
    // Thêm mới sản phẩm
    @Override
    public boolean addProduct(Product product) {
        return productDAO.addProduct(product);
    }

    @Override
    public Product findProductById(int productId) {
        return productDAO.findProductById(productId);
    }

    @Override
    public boolean updateProduct(Product product) {
        return productDAO.updateProduct(product);
    }

    @Override
    public boolean deleteProduct(Product product) {
        return productDAO.deleteProduct(product);
    }

    @Override
    public List<Product> findAllProductsByBrand(String brand) {
        return productDAO.findProductByBrand(brand);
    }

    @Override
    public List<Product> findProductByPrice(float price1, float price2) {
        return productDAO.findProductByPriceRange(price1, price2);
    }

    @Override
    public List<Product> findProductByStockRange(int stock1, int stock2) {
        return productDAO.findProductByStock(stock1,stock2);
    }
}
