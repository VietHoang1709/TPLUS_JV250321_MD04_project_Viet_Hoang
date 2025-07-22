package presentation;

import validation.Validator;

import java.util.Scanner;

public class AdminManagement {
    public static void displayAdmin(Scanner scanner) {
        boolean isExist = true;
        do {
            System.out.println("========== ĐĂNG NHẬP QUẢN TRỊ ==========");
            System.out.println("Tài khoản: ");
            String username = scanner.nextLine();
            System.out.println("Mật khẩu: ");
            String password = scanner.nextLine();
            System.out.println("========================================");
            String choice = scanner.nextLine();
            if (Validator.isInt(choice)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        Management.display(scanner);
                        break;
                    case 2:
                        isExist = false;
                        break;
                    default:
                        System.err.println("Vui long chon 1 - 2");
                        break;
                }
            }
        } while (true);
    }
}
