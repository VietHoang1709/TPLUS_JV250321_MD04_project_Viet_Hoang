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
            System.out.println("2. Thêm khách hàng mới");
            System.out.println("3. Cập nhập thông tin khách hàng");
            System.out.println("4. Xoá khách hàng theo ID");
            System.out.println("5. Quay lại menu chínnh");
            System.out.println("========================================");
            System.out.println("Lựa chọn của bạn: ");
            String choice = scanner.nextLine();
            if (Validator.isInt(choice)) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        displayCustomers();
                        break;
                    case 2:
                        addCustomer(scanner);
                        break;
                    case 3:
                        updateCustomer(scanner);
                        break;
                    case 4:
                        deleteCustomer(scanner);
                        break;
                    case 5:
                        isExist = false;
                        break;
                    default:
                        System.err.println("Vui lòng lựa chọn từ 1-5");
                }
            }else {
                System.err.println("Vui lòng nhập vào 1 số nguyên dương");
            }
        } while (isExist);
    }

    public void displayCustomers() {
        customerBusiness.findAllCustomers().forEach(System.out::println);
    }

    public void addCustomer(Scanner scanner) {
        Customer customer = new Customer();
        customer.setCustomerName(inputCustomerName(scanner, "Nhập tên khách hàng mới: "));
        customer.setCustomerPhone(inputCustomerPhone(scanner, "Nhập số điện thoại khách hàng: "));
        customer.setCustomerEmail(inputCustomerEmail(scanner, "Nhập email khách hàng: "));
        customer.setCustomerAddress(inputCustomerAddress(scanner, "Nhập địa chỉ khách hàng: "));
        customerBusiness.addCustomer(customer);
    }

    public String inputCustomerName(Scanner scanner, String message) {
        System.out.println(message);
        do {
            String customerName = scanner.nextLine();
            if (!Validator.isEmpty(customerName)) {
                return customerName;
            }
            System.err.println("Tên không được để trống. Vui lòng nhập vào tên khách hàng");
        } while (true);
    }

    public String inputCustomerPhone(Scanner scanner, String message) {
        System.out.println(message);
        do {
            String customerPhone = scanner.nextLine();
            if (Validator.isValidCustomerPhone(customerPhone)) {
                return customerPhone;
            }
            System.err.println("Số điện thoại không hợp lệ. Vui lòng nhập lại");
        } while (true);
    }

    public String inputCustomerEmail(Scanner scanner, String message) {
        System.out.println(message);
        do {
            String customerEmail = scanner.nextLine();
            if (Validator.isValidEmail(customerEmail)) {
                if (customerBusiness.checkExistCustomerEmail(customerEmail)) {
                    return customerEmail;
                }
                System.err.println("Email đã tồn tại. Vui lòng nhập lại");
            } else {
                System.err.println("Email không đúng định dang. Vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputCustomerAddress(Scanner scanner, String message) {
        System.out.println(message);
        String customerAddress = scanner.nextLine();
        return customerAddress;
    }

    public void updateCustomer(Scanner scanner) {
        System.out.println("Nhập mã khách hàng bạn muốn cập nhập:");
        int customerId;
        while (true) {
            String input = scanner.nextLine();
            if (Validator.isInt(input)) {
                customerId = Integer.parseInt(input);
                if (customerId > 0) {
                    break;
                } else {
                    System.err.println("ID phải là số nguyên dương. Vui lòng nhập lại:");
                }
            } else {
                System.err.println("Dữ liệu không hợp lệ. Vui lòng nhập số nguyên dương:");
            }
        }
        Customer customer = customerBusiness.findCustomerById(customerId);
        if (customer != null) {
            boolean isExist = true;
            do {
                System.out.println("1. Cập nhập tên khách hàng:");
                System.out.println("2. Cập nhập số điện thoại:");
                System.out.println("3. Cập nhập email:");
                System.out.println("4. Cập nhập địa chỉ:");
                System.out.println("5. Thoát");
                System.out.println("=============================");
                System.out.println("Lựa chọn của bạn:");
                String choice = scanner.nextLine();
                if (Validator.isInt(choice)) {
                    switch (Integer.parseInt(choice)) {
                        case 1:
                            customer.setCustomerName(inputCustomerName(scanner, "Nhập tên mới khách hàng: "));
                            break;
                        case 2:
                            customer.setCustomerPhone(inputCustomerPhone(scanner, "Nhập số điện thoại mới: "));
                            break;
                        case 3:
                            customer.setCustomerEmail(inputCustomerEmail(scanner, "Nhập email mới: "));
                            break;
                        case 4:
                            customer.setCustomerAddress(inputCustomerAddress(scanner, "Nhập địa chỉ mới: "));
                            break;
                        case 5:
                            isExist = false;
                            break;
                        default:
                            System.err.println("Vui lòng chọn từ 1-5");
                    }
                }
            } while (isExist);
            customerBusiness.updateCustomer(customer);
        } else {
            System.err.println("Mã khách hàng không tồn tại!");
        }
    }

    public void deleteCustomer(Scanner scanner) {
        System.out.println("Nhập mã khách hàng muốn xoá: ");
        int customerId;
        while (true) {
            String input = scanner.nextLine();
            if (Validator.isInt(input)) {
                customerId = Integer.parseInt(input);
                break;
            } else {
                System.err.println("Vui lòng nhập ID hợp lệ (số nguyên dương): ");
            }
        }

        Customer deleteCustomer = customerBusiness.findCustomerById(customerId);
        if (deleteCustomer != null) {
            System.out.println("Bạn có chắc chắn muốn xoá khách hàng này không? (Y/N)");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("Y")) {
                customerBusiness.deleteCustomer(deleteCustomer);
                System.out.println("Xoá thành công!");
            } else {
                System.out.println("Huỷ thao tác xoá.");
            }
        } else {
            System.err.println("Không tìm thấy mã khách hàng");
        }
    }
}
