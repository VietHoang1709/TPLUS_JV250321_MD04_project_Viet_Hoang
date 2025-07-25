package presentation;

import entity.InvoiceDetails;
import validation.Validator;

import java.util.Scanner;

public class Management {
    public void display(Scanner scanner) {
        ProductManagement productManagement = new ProductManagement();
        CustomerManagement customerManagement = new CustomerManagement();
        InvoiceManagement invoiceManagement = new InvoiceManagement();
        RevenueStatistics revenueStatistics = new RevenueStatistics();
        boolean flag = true;
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
                        productManagement.displayProduct(scanner);
                        break;
                    case 2:
                        customerManagement.displayCustomer(scanner);
                        break;
                    case 3:
                        invoiceManagement.displayInvoice(scanner);
                        break;
                    case 4:
                        revenueStatistics.displayRevenueStatistics(scanner);
                        break;
                    case 5:
                        flag = false;
                        break;
                    default:
                        System.err.println("Vui lòng nhập từ 1-5");
                }
            }
        } while (flag);
    }
}
