package business.imp;

import business.ProductBusiness;
import dao.ProductDAO;
import dao.imp.ProductDaoImp;
import entity.Product;

import java.util.List;

public class ProductBusinessImp implements ProductBusiness {
    private ProductDAO productDAO;

    public ProductBusinessImp() {
        productDAO = new ProductDaoImp();
    }

    @Override
    public List<Product> findAllProducts() {
        return productDAO.findAllProduct();
    }
}
