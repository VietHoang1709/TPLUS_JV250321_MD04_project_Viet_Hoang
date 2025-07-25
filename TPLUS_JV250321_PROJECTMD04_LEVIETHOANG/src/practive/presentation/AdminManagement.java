package presentation;

import business.AdminBusiness;
import business.imp.AdminBusinessImp;
import entity.Admin;
import validation.Validator;

import java.util.Scanner;

public class AdminManagement {
    private AdminBusiness adminBusiness;

    public AdminManagement() {
        adminBusiness = new AdminBusinessImp();
    }

    public void displayAdmin(Scanner scanner) {
        Management management = new Management();
        boolean isLoggedIn = false;
        do {
            System.out.println("========== ĐĂNG NHẬP QUẢN TRỊ ==========");
            System.out.println("Tài khoản: ");
            String username = scanner.nextLine();
            System.out.println("Mật khẩu: ");
            String password = scanner.nextLine();
            System.out.println("========================================");
            Admin admin = adminBusiness.login(username,password);
            if(admin!=null){
                System.out.println("Đăng nhập thành công! Chào" + admin.getUsername());
                  management.display(scanner);
            }else{
                System.err.println("Tên đăng nhập hoặc mật khẩu không đúng");
            }
        } while (!isLoggedIn);
    }
}
