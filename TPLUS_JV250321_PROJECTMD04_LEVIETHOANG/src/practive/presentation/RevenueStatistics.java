package presentation;

import business.InvoiceBusiness;
import business.imp.InvoiceBusinessImp;
import entity.Invoice;
import validation.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class RevenueStatistics {
    private InvoiceBusiness invoiceBusiness;

    public RevenueStatistics() {
        invoiceBusiness = new InvoiceBusinessImp();
    }

    public void displayRevenueStatistics(Scanner scanner) {
        boolean flag = true;
        do {
            System.out.println("========== Thống kê doanh thu ==========");
            System.out.println("1. Doanh thu theo ngày");
            System.out.println("2. Doanh thu theo tháng");
            System.out.println("3. Doanh thu theo năm");
            System.out.println("4. Quay lại menu chính");
            System.out.println("===========================================");
            String choice = scanner.nextLine();
            if (Validator.isInt(choice)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        invoiceBusiness.getAllRevenueByDay(inputDate(scanner, "Nhâp ngày/tháng/năm(dd/MM/yyyy)"));
                        break;
                    case 2:
                        int month = inputMonth(scanner);
                        int year = inputYear(scanner);
                        invoiceBusiness.getAllRevenueByMonth(month,year);
                        break;
                    case 3:
                        year = inputYear(scanner);
                        invoiceBusiness.getAllRevenueByYear(year);
                        break;
                    case 4:
                        flag = false;
                        break;
                    default:
                        System.err.println("Vui lòng chọn từ 1-4");
                }
            }
        } while (flag);
    }

    public LocalDate inputDate(Scanner scanner, String message) {
        System.out.println(message);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        do {
            String inputDate = scanner.nextLine();
            try {
                return LocalDate.parse(inputDate, formatter);
            } catch (DateTimeParseException e) {
                System.err.println("Sai định dạng! Vui lòng nhập theo định dạng dd/MM/yyyy.");
            }
        } while (true);
    }

    public int inputMonth(Scanner scanner) {
        do {
            int month;
            System.out.print("Nhập tháng (1-12): ");
            String input = scanner.nextLine();
            if (Validator.isInt(input)) {
                month = Integer.parseInt(input);
                if (month >= 1 && month <= 12) {
                    return month;
                } else {
                    System.err.println("Tháng phải nằm trong khoảng từ 1 đến 12.");
                }
            } else {
                System.err.println("Vui lòng nhập số nguyên.");
            }
        } while (true);
    }

    public int inputYear(Scanner scanner) {
        do {
            int year;
            int currentYear = LocalDate.now().getYear();
            System.out.print("Nhập năm (<= " + currentYear + "): ");
            String input = scanner.nextLine();
            if (Validator.isInt(input)) {
                year = Integer.parseInt(input);
                if (year <= currentYear) {
                    return year;
                } else {
                    System.err.println("Năm phải nhỏ hơn hoặc bằng năm hiện tại (" + currentYear + ").");
                }
            } else {
                System.err.println("Vui lòng nhập số nguyên.");
            }
        } while (true);
    }
}
