package presentation;

import business.CustomerBusiness;
import business.imp.CustomerBusinessImp;
import entity.Customer;
import validation.Validator;

import java.util.Scanner;

public class CustomerManagement {
    private final CustomerBusiness customerBusiness;
    public CustomerManagement() {
        customerBusiness = new CustomerBusinessImp();
    }
    public void displayCustomer(Scanner scanner) {
        boolean isExist = true;
        do {
            System.out.println("========== QUẢN LÝ KHÁCH HÀNG ==========");
            System.out.println("1. Hiện thị danh sách khách hàng");
            System.out.println("2. Thêm khác hàng mới");
            System.out.println("3. Cập nhập thông tin khách hàng");
            System.out.println("4. Xoá khách hàng theo ID");
            System.out.println("5. Quay lại menu chínnh");
            System.out.println("========================================");
            System.out.println("Lựa chọn của bạn: ");
            String choice = scanner.nextLine();
            if (Validator.isInt(choice)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        displayCustomers(scanner);
                        break;
                    case 2:
                        addCustomer(scanner);
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    default:
                        System.err.println("Vui lòng lựa chọn từ 1-5");
                }
            }
        } while (true);
    }
    public void displayCustomers(Scanner scanner) {
        customerBusiness.findAllCustomers().forEach(System.out::println);
    }
    public void addCustomer(Scanner scanner) {
        Customer customer = new Customer();
        customer.setCustomerName(inputCustomerName(scanner,"Nhập tên khách hàng mới: "));
        customer.setCustomerPhone(inputCustomerPhone(scanner,"Nhập số điện thoại khách hàng: "));
        customer.setCustomerEmail(inputCustomerEmail(scanner,"Nhập email khách hàng: "));
        customer.setCustomerAddress(inputCustomerAddress(scanner,"Nhập địa chỉ khách hàng: "));
        customerBusiness.addCustomer(customer);
    }
    public String inputCustomerName(Scanner scanner, String message) {
        System.out.println(message);
        do{
            String customerName = scanner.nextLine();
            if(!Validator.isEmpty(customerName)){
                return customerName;
            }
            System.err.println("Tên không được để trống. Vui lòng nhập vào tên khách hàng");
        }while(true);
    }
    public String inputCustomerPhone(Scanner scanner, String message) {
        System.out.println(message);
        do{
            String customerPhone = scanner.nextLine();
            if(Validator.isValidCustomerPhone(customerPhone)){
                return customerPhone;
            }
            System.err.println("Số điện thoại không hợp lệ. Vui lòng nhập lại");
        }while(true);
    }
    public String inputCustomerEmail(Scanner scanner, String message) {
        System.out.println(message);
        do{
            String customerEmail = scanner.nextLine();
            if(Validator.isValidEmail(customerEmail)){
                if(customerBusiness.checkExistCustomerEmail(customerEmail)){
                    return customerEmail;
                }
                System.err.println("Email đã tồn tại. Vui lòng nhập lại");
            }else{
                System.err.println("Email không đúng định dang. Vui lòng nhập lại");
            }
        }while(true);
    }
    public String inputCustomerAddress(Scanner scanner, String message) {
        System.out.println(message);
        do{
            String customerAddress = scanner.nextLine();
                return customerAddress;
        }while(true);
    }
}
