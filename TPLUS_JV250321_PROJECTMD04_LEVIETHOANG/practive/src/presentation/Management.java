package presentation;

import validation.Validator;

import java.util.Scanner;

public class Management {
    public static boolean display(Scanner scanner) {
        do {
            System.out.println("========== MENU CHÍNH ==========");
            System.out.println("1. Quản lý sản phẩm điện thoại");
            System.out.println("2. Quản lý khách hàng");
            System.out.println("3. Quản lý hoá đơn");
            System.out.println("4. Thống kê doanh thu");
            System.out.println("5. Đăng xuất");
            System.out.println("================================");
            System.out.println("Nhập lựa chọn: ");
            String choice = scanner.nextLine();
            if (Validator.isInt(choice)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        ProductManagement.displayProduct(scanner);
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        return true;
                    default:
                        System.err.println("Vui lòng nhập từ 1-5");
                }
            }
        } while (true);
    }
}
