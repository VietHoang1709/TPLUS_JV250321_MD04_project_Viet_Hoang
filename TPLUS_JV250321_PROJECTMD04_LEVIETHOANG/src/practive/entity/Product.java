package entity;

import validation.Validator;

import java.util.Scanner;

public class Product {
    private int productId;
    private String productName;
    private String ProductBrand;
    private float price;
    private int stock;

    public Product() {
    }
    public Product(int productId, String productName, String productBrand, float price, int stock) {
        this.productId = productId;
        this.productName = productName;
        ProductBrand = productBrand;
        this.price = price;
        this.stock = stock;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBrand() {
        return ProductBrand;
    }

    public void setProductBrand(String productBrand) {
        ProductBrand = productBrand;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
//    public void inputData(Scanner scanner) {
//        String productName = inputProductName(scanner);
//        String productBrand = inputProductBrand(scanner);
//        float price = inputPrice(scanner);
//        int stock = inputStock(scanner);
//
//        setProductName(productName);
//        setProductBrand(productBrand);
//        setPrice(price);
//        setStock(stock);
//    }
//
//    public static String inputProductName(Scanner scanner) {
//        String input;
//        do {
//            try {
//                System.out.print("Nhập tên sản phẩm: ");
//                input = scanner.nextLine();
//                if (Validator.isEmpty(input)) {
//                    System.err.println("Tên không được để trống");
//                } else if (input.length() > 100) {
//                    System.err.println("Tên không dài quá 100 kí tự");
//                } else {
//                    return input;
//                }
//            } catch (Exception e) {
//                System.err.println("Lỗi khi nhập tên sản phẩm!");
//            }
//        } while (true);
//    }
//
//    public static String inputProductBrand(Scanner scanner) {
//        String input;
//        do {
//            try {
//                System.out.print("Nhập tên nhãn hàng: ");
//                input = scanner.nextLine();
//                if (Validator.isEmpty(input)) {
//                    System.err.println("Tên nhãn hàng không được để trống");
//                } else if (input.length() > 50) {
//                    System.err.println("Tên nhãn hàng dài không quá 50 kí tự");
//                } else {
//                    return input;
//                }
//            } catch (Exception e) {
//                System.err.println("Lỗi khi nhập nhãn hàng!");
//            }
//        } while (true);
//    }
//    public static float inputPrice(Scanner scanner) {
//        String input;
//        do {
//            try {
//                System.out.print("Nhập giá: ");
//                input = scanner.nextLine();
//                if (!Validator.isFloat(input)) {
//                    System.err.println("Giá phải là số thực hợp lệ");
//                } else {
//                    float price = Float.parseFloat(input);
//                    if (price <= 0) {
//                        System.err.println("Giá phải lớn hơn 0");
//                    } else {
//                        return price;
//                    }
//                }
//            } catch (Exception e) {
//                System.err.println("Lỗi khi nhập giá!");
//            }
//        } while (true);
//    }
//    public static int inputStock(Scanner scanner) {
//        String input;
//        do {
//            try {
//                System.out.print("Nhập số lượng tồn kho: ");
//                input = scanner.nextLine();
//                if (!Validator.isInt(input)) {
//                    System.err.println("Số lượng phải là số nguyên hợp lệ");
//                } else {
//                    int stock = Integer.parseInt(input);
//                    if (stock < 0) {
//                        System.err.println("Số lượng không được âm");
//                    } else {
//                        return stock;
//                    }
//                }
//            } catch (Exception e) {
//                System.err.println("Lỗi khi nhập số lượng tồn kho!");
//            }
//        } while (true);
//    }
    @Override
    public String toString() {
        return String.format("Mã sản phẩm: %d | Tên sản phẩm: %s | Nhãn hàng: %s | Giá bán: %2f | Số lượng tồn kho: %d",
                this.productId, this.productName, this.ProductBrand, this.price, this.stock);
    }
}
