package presentation;

import business.ProductBusiness;
import business.imp.ProductBusinessImp;
import validation.Validator;

import java.util.Scanner;

public class ProductManagement {
    private final ProductBusiness productBusiness;

    public ProductManagement() {
        productBusiness = new ProductBusinessImp();
    }

    public static void displayProduct(Scanner scanner) {
        ProductManagement productManagement = new ProductManagement();
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
            String choice = scanner.nextLine();
            if (Validator.isInt(choice)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        displayAllProducts(productManagement);
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        isExist = false;
                    default:
                        System.err.println("Vui long chon lai tu 1-8");
                }
            }
        } while (isExist);
    }
    public static void displayAllProducts(ProductManagement productManagement) {
        productManagement.productBusiness.findAllProducts().forEach(System.out::println);
    }
}
