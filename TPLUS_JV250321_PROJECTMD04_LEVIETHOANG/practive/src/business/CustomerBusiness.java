package business;

import entity.Customer;

import java.util.List;

public interface CustomerBusiness {
    List<Customer> findAllCustomers();
    boolean checkExistCustomerEmail(String email);
    boolean addCustomer(Customer customer);
}
