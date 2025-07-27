package presentation;

import business.InvoiceBusiness;
import business.InvoiceDetailsBusiness;
import business.ProductBusiness;
import business.imp.CustomerBusinessImp;
import business.imp.InvoiceBusinessImp;
import business.imp.InvoiceDetailsBusinessImp;
import business.imp.ProductBusinessImp;
import entity.Customer;
import entity.Invoice;
import entity.InvoiceDetails;
import entity.Product;
import validation.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class InvoiceManagement {
    private CustomerBusinessImp customerBusinessImp;
    private InvoiceBusiness invoiceBusiness;
    private InvoiceDetailsBusiness invoiceDetailsBusiness;
    private ProductBusiness productBusiness;

    public InvoiceManagement() {
        invoiceBusiness = new InvoiceBusinessImp();
        invoiceDetailsBusiness = new InvoiceDetailsBusinessImp();
        customerBusinessImp = new CustomerBusinessImp();
        productBusiness = new ProductBusinessImp();
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
                        System.out.println("| Mã hoá đơn | Mã khách hàng | Tên khách hàng | Ngày tạo hoá đơn | Tổng giá trị hoá đơn | Tên sản phẩm         | Số lượng bán |");
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
        float totalAmount = 0;
        Invoice invoice = new Invoice();
        do {
            int customerID = inputCustomerId(scanner, "Nhập mã khách hàng:");
            if (customerBusinessImp.findCustomerById(customerID) == null) {
                System.err.println("không tìm thấy mã khách hàng. Vui lòng nhập lại");
            } else {
                invoice.setCustomerId(customerID);
                break;
            }
        } while (true);
        int invoiceId = invoiceBusiness.addInvoice(invoice);
        System.out.println("Mã HD: "+invoiceId);
        do {
            InvoiceDetails invoiceDetails = new InvoiceDetails();
            Product product = new Product();
// Kiem tra ma khac hang ton tai hay khong
            // Nhap va kiem tra ma san pham
            do {
                int checkProductId = inputProductId(scanner, "Nhập mã sản phẩm:");
                product = productBusiness.findProductById(checkProductId);
                if (product == null) {
                    System.err.println("Không tìm thấy sản phẩm. Vui lòng nhập lại");
                } else {
                    invoiceDetails.setProductId(checkProductId);
                    invoiceDetails.setUnitPrice(product.getPrice());
                    break;
                }
            } while (true);
            // Nhap va kiem so luong san pham mua va ton kho
            do {
                int checkQuantity = inputQuantity(scanner, "Nhập vào số lượng mua:");
                if (product.getStock() < checkQuantity) {
                    System.err.println("Số lương tồn kho không đủ");
                } else {
                    invoiceDetails.setQuantity(checkQuantity);
                    break;
                }
            } while (true);
            if (invoiceId != -1) {
                invoiceDetails.setInvoiceId(invoiceId);
                boolean isSuccess = invoiceDetailsBusiness.addInvoiceDetails(invoiceDetails);
                // Them hoa don chi tiet thanh cong -> Update so luong ton kho,
                if (isSuccess) {
                    product.setStock(product.getStock() - invoiceDetails.getQuantity());
                    boolean isUpdateProduct = productBusiness.updateProduct(product);
                    if (isUpdateProduct) {
                        totalAmount += (invoiceDetails.getQuantity() * invoiceDetails.getUnitPrice());
                    }
                }
            }
            System.out.println("Bạn có muốn thêm sản phẩm hơn đơn? (Y/N)");
            boolean isOut = false;
            do {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("Y")) {
                    break;
                }
                else if (input.equalsIgnoreCase("N")) {
                    isOut = true;
                    break;
                }else{
                    System.err.println("Chỉ nhập Y hoặc N ");
                }
            } while (true);
            if(isOut){
                invoiceBusiness.updateInvoice(invoiceId,totalAmount);
                break;
            }
        } while (true);
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

    public void findInvoiceByCustomerName(Scanner scanner) {
        System.out.println("Nhập tên khách hàng: ");
        List<Invoice> findInvoicesByCustomerName = invoiceBusiness.getInvoicesByCustomerName(scanner.nextLine());
        if (!findInvoicesByCustomerName.isEmpty()) {
            findInvoicesByCustomerName.forEach(System.out::println);
        } else {
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
        LocalDate date = inputDate(scanner, "Nhập ngày/tháng/năm muốn tiềm kiếm(dd//MM/yyyy):");
        List<Invoice> invoiceList = invoiceBusiness.getInvoicesByCreatedAt(date);
        if (!invoiceList.isEmpty()) {
            invoiceList.forEach(System.out::println);
        } else {
            System.err.println("Không có hoá đơn phù hợp ngày nhập vào");
        }
    }

    private int inputProductId(Scanner scanner, String message) {
        System.out.println(message);
        do {
            String productId = scanner.nextLine();
            if (!Validator.isEmpty(productId)) {
                int id = Integer.parseInt(productId);
                if (productBusiness.findProductById(id) != null) {
                    return id;
                } else {
                    System.err.println("Không tìm thấy mã sản phẩm. Vui lòng nhập lại");
                }
            } else {
                System.err.println("Vui lòng nhập số nguyên");
            }
        }
        while (true);
    }

    private int inputQuantity(Scanner scanner, String message) {
        System.out.println(message);
        do {
            String inputQuantity = scanner.nextLine();
            if (!Validator.isEmpty(inputQuantity) && Validator.isInt(inputQuantity)) {
                int quantity = Integer.parseInt(inputQuantity);
                if (quantity > 0) {
                    return quantity;
                } else {
                    System.err.println("Số lượng tồn kho phải lớn hơn không");
                }
            } else {
                System.err.println("Vui lòng nhập vào số nguyên");
            }
        }
        while (true);
    }

}

