package dao.imp;

import dao.CustomerDAO;
import entity.Customer;
import util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImp implements CustomerDAO {
    @Override
    public List<Customer> findAllCustomers() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Customer> customers = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_all_customer()}");
            ResultSet rs = callSt.executeQuery();
            customers = new ArrayList<>();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setCustomerName(rs.getString("customer_name"));
                customer.setCustomerPhone(rs.getString("phone"));
                customer.setCustomerEmail(rs.getString("email"));
                customer.setCustomerAddress(rs.getString("address"));
                customers.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return customers;
    }

    @Override
    public boolean checkExistEmail(String email) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call check_exist_customer_email(?,?)}");
            callSt.setString(1, email);
            callSt.registerOutParameter(2, Types.INTEGER);
            callSt.execute();
            int cntCustomer = callSt.getInt(2);
            if (cntCustomer == 0) {
                return true; // Chua ton tai
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false; // da ton tai
    }
    @Override
    public boolean addCustomer(Customer customer) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call add_customer(?,?,?,?)}");
            callSt.setString(1, customer.getCustomerName());
            callSt.setString(2, customer.getCustomerPhone());
            callSt.setString(3, customer.getCustomerEmail());
            callSt.setString(4, customer.getCustomerAddress());
            callSt.executeUpdate();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }
}
