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
}
