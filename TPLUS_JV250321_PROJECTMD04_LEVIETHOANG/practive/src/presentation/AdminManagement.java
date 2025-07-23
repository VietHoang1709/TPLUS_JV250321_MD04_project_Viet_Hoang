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

        } while (true);
    }
}
