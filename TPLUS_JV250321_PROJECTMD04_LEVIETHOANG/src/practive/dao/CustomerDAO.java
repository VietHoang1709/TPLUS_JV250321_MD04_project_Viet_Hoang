package dao;

import entity.Customer;

import java.util.List;

public interface CustomerDAO {
    List<Customer> findAllCustomers();
    boolean checkExistEmail(String email);
    boolean addCustomer(Customer customer);
    Customer findCustomerById(int id);
    boolean updateCustomer(Customer customer);
    boolean deleteCustomerById(Customer customer);
}
