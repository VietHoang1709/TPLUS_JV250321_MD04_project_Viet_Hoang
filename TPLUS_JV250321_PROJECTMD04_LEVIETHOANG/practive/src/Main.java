import validation.Validator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("========== HỆ THỐNG QUẢN LÝ CỬA HÀNG ==========");
            System.out.println("1.Đăng nhập Admin");
            System.out.println("2.Thoát");
            System.out.println("===============================================");
            System.out.println("Nhập lựa chọn: ");
            String choice =  scanner.nextLine();
            if (Validator.isInt(choice)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        break;
                        case 2:
                            System.out.println("Cam on da su dung chuong trinh");
                            System.exit(0);
                    default:
                        System.err.println("Vui long chon tu 1 - 2");
                }
            }
        }while(true);
    }
}
