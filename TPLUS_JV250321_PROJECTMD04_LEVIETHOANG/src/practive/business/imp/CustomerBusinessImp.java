package business.imp;

import business.CustomerBusiness;
import dao.CustomerDAO;
import dao.imp.CustomerDaoImp;
import entity.Customer;

import java.util.List;

public class CustomerBusinessImp implements CustomerBusiness {
    private final CustomerDAO customerDAO;
    public CustomerBusinessImp() {
        customerDAO = new CustomerDaoImp();
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerDAO.findAllCustomers();
    }

    @Override
    public boolean checkExistCustomerEmail(String email) {
        return customerDAO.checkExistEmail(email);
    }

    @Override
    public boolean addCustomer(Customer customer) {
        return customerDAO.addCustomer(customer);
    }

    @Override
    public Customer findCustomerById(int id) {
        return customerDAO.findCustomerById(id);
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return customerDAO.updateCustomer(customer) ;
    }

    @Override
    public boolean deleteCustomer(Customer customer) {
        return customerDAO.deleteCustomerById(customer);
    }
}
