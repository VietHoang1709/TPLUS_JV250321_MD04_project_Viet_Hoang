package presentation;

import business.ProductBusiness;
import business.imp.ProductBusinessImp;
import entity.Product;
import validation.Validator;

import java.util.List;
import java.util.Scanner;

public class ProductManagement {
    private final ProductBusiness productBusiness;

    public ProductManagement() {
        productBusiness = new ProductBusinessImp();
    }

    //    ProductManagement productManagement = new ProductManagement();
    public void displayProduct(Scanner scanner) {
        boolean isExist = true;
        do {
            System.out.println("========== QUẢN LÝ SẢN PHẨM ==========");
            System.out.println("1. Hiển thị danh sách sản phẩm");
            System.out.println("2. Thêm mới sản phẩm");
            System.out.println("3. Cập nhập thông tin sản phẩm");
            System.out.println("4. Xoá sản phẩm theo ID");
            System.out.println("5. Tìm kiếm theo Brand");
            System.out.println("6. Tìm kiếm theo khoảng giá");
            System.out.println("7. Tìm kiến theo tồn kho");
            System.out.println("8. Quay lại menu chính");
            System.out.println("======================================");
            System.out.println("Nhập lựa chọn của bạn: ");
            String choice = scanner.nextLine();
            if (Validator.isInt(choice)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        displayAllProducts();
                        break;
                    case 2:
                        addProduct(scanner);
                        break;
                    case 3:
                        updateProduct(scanner);
                        break;
                    case 4:
                        deleteProduct(scanner);
                        break;
                    case 5:
                        findAllProductsByBrand(scanner);
                        break;
                    case 6:
                        findProductByPriceRange(scanner);
                        break;
                    case 7:
                        findProductByStockRange(scanner);
                        break;
                    case 8:
                        isExist = false;
                        break;
                    default:
                        System.err.println("Vui long chon lai tu 1-8");
                }
            }
        } while (isExist);
    }

    public void displayAllProducts() {
        productBusiness.findAllProducts().forEach(System.out::println);
    }

    public void addProduct(Scanner scanner) {
        Product productAdd = new Product();
        productAdd.setProductName(inputName(scanner,"Nhập tên sản phẩm mới:"));
        productAdd.setProductBrand(inputBrand(scanner,"Nhập tên nhãn hàng:"));
        productAdd.setPrice(inputPrice(scanner,"Nhập giá sản phẩm mới:"));
        productBusiness.addProduct(productAdd);
    }

    public void updateProduct(Scanner scanner) {
        System.out.println("Nhập mã sản phẩm muốn cập nhập: ");
        Product productUpdate = productBusiness.findProductById(scanner.nextInt());
        if (productUpdate != null) {
            boolean isExist = true;
            do {
                System.out.println("1. Cập nhập tên sản phẩm: ");
                System.out.println("2. Cập nhập tên nhãn hàng: ");
                System.out.println("3. Cập nhập giá sản phẩm: ");
                System.out.println("4. Cập nhập số lượng tồn kho: ");
                System.out.println("5. Thoát");
                System.out.println("Lựa chọn của bạn");
                String choice = scanner.nextLine();
                if (Validator.isInt(choice)) {
                    switch (Integer.parseInt(choice)) {
                        case 1:
                            productUpdate.setProductName(inputName(scanner, "Nhập tên sản phẩm: "));
                            break;
                        case 2:
                            productUpdate.setProductBrand(inputBrand(scanner, "Nhập tên brand: "));
                            break;
                        case 3:
                            productUpdate.setPrice(inputPrice(scanner, "Nhập giá sản phẩm: "));
                            break;
                        case 4:
                            productUpdate.setStock(inputStock(scanner, "Nhập số lượng tồn kho: "));
                            break;
                        case 5:
                            isExist = false;
                            break;
                        default:
                            System.err.println("Vui lòng chọn từ 1-5");
                            break;
                    }
                }
            } while (isExist);
            productBusiness.updateProduct(productUpdate);
        } else {
            System.err.println("Mã sản phẩm không tồn tại");
        }
    }

    public void deleteProduct(Scanner scanner) {
        System.out.println("Nhập mã sản phẩm muốn xoá: ");
        Product productDelete = productBusiness.findProductById(scanner.nextInt());
        if (productDelete != null) {
            productBusiness.deleteProduct(productDelete);
        } else {
            System.err.println("Không tìm thấy mã sản phẩm");
        }
    }

    public void findAllProductsByBrand(Scanner scanner) {
        System.out.println("Nhập tên nhãn hàng: ");
        List<Product> productFindByBrand = productBusiness.findAllProductsByBrand(scanner.nextLine());
        productFindByBrand.forEach(System.out::println);
    }

    public void findProductByPriceRange(Scanner scanner) {
        do {
            float startPrice;
            System.out.println("Nhập giá thấp nhất trong khoảng muốn tìm kiếm: ");
            String inputStartPrice = scanner.nextLine();
            if (Validator.isFloat(inputStartPrice)) {
                startPrice = Float.parseFloat(inputStartPrice);
                if (startPrice < 0) {
                    System.err.println("Giá không được nhỏ hơn 0");
                    continue;
                }
                System.out.println("Nhập gía cao nhất trong khoảng muốn tìm kiếm: ");
                String inputEndPrice = scanner.nextLine();
                if (Validator.isFloat(inputEndPrice)) {
                    float endPrice = Float.parseFloat(inputEndPrice);
                    if (endPrice < startPrice) {
                        continue;
                    }
                    List<Product> productList = productBusiness.findProductByPrice(startPrice, endPrice);
                    productList.forEach(System.out::println);
                } else {
                    System.err.println("Giá cao nhất phải lớn hơn giá thấp nhất");
                }
            } else {
                System.err.println("Giá nhập vào phải là số thực lớn hơn 0");
            }
        } while (true);
    }

    public void findProductByStockRange(Scanner scanner) {
        int startStock;
        System.out.println("Nhập số lượng tồn kho thấp nhất: ");
        String inputStartStock = scanner.nextLine();
        if (Validator.isInt(inputStartStock)) {
            startStock = Integer.parseInt(inputStartStock);
            if (startStock < 0) {
                System.err.println("Số lượng tồn kho phải lớn hơn 0");
                return;
            }
            System.out.println("Nhập số lượng lớn nhất trong khoảng muốn tìm kiếm:");
            String inputEndStock = scanner.nextLine();
            if (Validator.isInt(inputEndStock)) {
                int endStock = Integer.parseInt(inputEndStock);
                if (endStock < startStock) {
                    return;
                }
                List<Product> productList = productBusiness.findProductByStockRange(startStock, endStock);
            } else {
                System.err.println("Số lượng lớn nhất phải lớn hơn số nhỏ nhất");
            }
        } else {
            System.err.println("Số lượng tồn kho phải là số nguyên dương");
        }
    }

    private String inputName(Scanner scanner, String message) {
        System.out.println(message);
        do {
            String productName = scanner.nextLine();
            if (!Validator.isEmpty(productName)) {
                return productName;
            }
            System.err.println("Tên sản phẩm không được để trống. Vui lòng nhập tên sản phẩm");
        } while (true);
    }

    private String inputBrand(Scanner scanner, String message) {
        System.out.println(message);
        do {
            String productBrand = scanner.nextLine();
            if (!Validator.isEmpty(productBrand)) {
                return productBrand;
            }
            System.err.println("Không được để trống. Vui lòng nhập tên sản phẩm");
        } while (true);
    }

    private float inputPrice(Scanner scanner,String message) {
        System.out.println(message);
        do {
            String price = scanner.nextLine();
            if (!Validator.isEmpty(price) && Validator.isFloat(price)) {
                if (Float.parseFloat(price) > 0) {
                    return Float.parseFloat(price);
                }
                    System.err.println("Giá phải nhập vào phải lớn hơn 0");
            } else {
                System.err.println("Không được để trống. Giá nhập vào phải là 1 số thực ");
            }
        } while (true);
    }

    private int inputStock(Scanner scanner,String message) {
        System.out.println(message);
        do {
            String stock = scanner.nextLine();
            if (!Validator.isEmpty(stock) && Validator.isInt(stock)) {
                if (Integer.parseInt(stock) > 0) {
                   return Integer.parseInt(stock);
                }
                    System.err.println("Số lượng nhập vào phải lớn hơn 0");
            } else {
                System.err.println("Số lượng không được bỏ trống và phải là số nguyên");
            }
        } while (true);
    }
}