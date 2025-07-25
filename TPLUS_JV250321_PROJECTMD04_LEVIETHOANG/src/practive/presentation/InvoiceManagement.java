package presentation;

import business.InvoiceBusiness;
import business.imp.CustomerBusinessImp;
import business.imp.InvoiceBusinessImp;
import entity.Customer;
import entity.Invoice;
import validation.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class InvoiceManagement {
    private CustomerBusinessImp customerBusinessImp = new CustomerBusinessImp();
    private InvoiceBusiness invoiceBusiness;

    public InvoiceManagement() {
        invoiceBusiness = new InvoiceBusinessImp();
    }

    public void displayInvoice(Scanner scanner) {
        boolean isExist = true;
        do {
            System.out.println("1. Hiển thị danh sách hoá đơn");
            System.out.println("2. Thêm mới hoá đơn");
            System.out.println("3. Tìm kiếm hoá đơn");
            System.out.println("4. Quay lại menu chính");
            System.out.println("====================================");
            System.out.println("Lựa chọn của bạn:");
            String choice = scanner.nextLine();
            if (Validator.isInt(choice)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        displayInvoices();
                        break;
                    case 2:
                        addInvoice(scanner);
                        break;
                    case 3:
                        displayInvoiceByDateOrByCustomerName(scanner);
                        break;
                    case 4:
                        isExist = false;
                        break;
                    default:
                        System.err.println("Lựa chọn của bạn: ");
                }
            } else {
                System.err.println("Vui lòng nhập vào 1 số nguyên dương");
            }
        } while (isExist);
    }

    public void displayInvoices() {
        invoiceBusiness.getInvoices().forEach(System.out::println);
    }

    public void addInvoice(Scanner scanner) {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(inputCustomerId(scanner, "Nhập mã khách hàng:"));
        invoice.setTotalAmount(inputAmount(scanner, "Nhập tổng tiền:"));
        invoiceBusiness.addInvoice(invoice);
    }

    private int inputCustomerId(Scanner scanner, String message) {
        customerBusinessImp.findAllCustomers().forEach(System.out::println);
        System.out.println(message);
        do {
            String inputCustomerId = scanner.nextLine();
            if (Validator.isInt(inputCustomerId)) {
                int id = Integer.parseInt(inputCustomerId);
                if (customerBusinessImp.findCustomerById(id) != null) {
                    return id;
                } else {
                    System.err.println("Mã khách hàng không tồn tại. Vui lòng nhập lại.");
                }
            } else {
                System.err.println("Vui lòng nhập số nguyên.");
            }
        } while (true);
    }

    public void displayInvoiceByDateOrByCustomerName(Scanner scanner) {
        boolean isExist = true;
        do {
            System.out.println("1. Tìm kiếm theo tên khách hàng");
            System.out.println("2. Tìm kiếm theo ngày/tháng/năm");
            System.out.println("3. Quay lại menu hoá đơn");
            System.out.println("==============================================");
            System.out.println("Lựa chọn của bạn: ");
            String choice = scanner.nextLine();
            if (Validator.isInt(choice)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        findInvoiceByCustomerName(scanner);
                        break;
                    case 2:
                        findInvoiceByDate(scanner);
                        break;
                    case 3:
                        isExist = false;
                        break;
                    default:
                        System.err.println("Vui lòng chọn từ 1-3");
                }
            }

        } while (isExist);
    }

    private float inputAmount(Scanner scanner, String message) {
        System.out.println(message);
        do {
            String inputAmount = scanner.nextLine();
            if (Validator.isFloat(inputAmount)) {
                float amount = Float.parseFloat(inputAmount);
                if (amount > 0) {
                    return amount;
                }
                System.err.println("Tổng tiền không được là số âm");
            } else {
                System.err.println("Vui lòng nhập vào số thực!");
            }
        } while (true);
    }

    public void findInvoiceByCustomerName(Scanner scanner) {
        System.out.println("Nhập tên khách hàng: ");
        List<Invoice> findInvoicesByCustomerName = invoiceBusiness.getInvoicesByCustomerName(scanner.nextLine());
        if (!findInvoicesByCustomerName.isEmpty()) {
            findInvoicesByCustomerName.forEach(System.out::println);
        }else {
            System.err.println("Tên khách hàng không tồn tại");
        }
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
    public void findInvoiceByDate(Scanner scanner) {
        LocalDate date = inputDate(scanner,"Nhập ngày/tháng/năm muốn tiềm kiếm(dd//MM/yyyy):");
        List<Invoice> invoiceList = invoiceBusiness.getInvoicesByCreatedAt(date);
        if(!invoiceList.isEmpty()) {
            invoiceList.forEach(System.out::println);
        }else{
            System.err.println("Không có hoá đơn phù hợp ngày nhập vào");
        }
    }
}

