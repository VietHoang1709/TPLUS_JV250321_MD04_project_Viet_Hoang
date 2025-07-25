import presentation.AdminManagement;
import presentation.CustomerManagement;
import presentation.InvoiceManagement;
import presentation.ProductManagement;
import validation.Validator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AdminManagement adminManagement = new AdminManagement();
        do {
            System.out.println("========== HỆ THỐNG QUẢN LÝ CỬA HÀNG ==========");
            System.out.println("1.Đăng nhập Admin");
            System.out.println("2.Thoát");
            System.out.println("===============================================");
            System.out.println("Nhập lựa chọn: ");
            String choice = scanner.nextLine();
            if (Validator.isInt(choice)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        adminManagement.displayAdmin(scanner);
                        break;
                    case 2:
                        System.out.println("Cam on da su dung chuong trinh");
                        System.exit(0);
                    default:
                        System.err.println("Vui long chon tu 1 - 2");
                }
            }
        } while (true);
    }
}
