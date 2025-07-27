package dao.imp;

import dao.InvoiceDAO;
import entity.Invoice;
import util.ConnectionDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDaoImp implements InvoiceDAO {

    @Override
    public List<Invoice> getAllInvoices() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Invoice> invoices = new ArrayList<>();
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_all_invoice()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setInvoiceId(rs.getInt("invoice_id"));
                invoice.setCustomerId(rs.getInt("customer_id"));
                invoice.setCustomerName(rs.getString("customer_name"));
                invoice.setCreatedAt(LocalDate.from(rs.getTimestamp("created_at").toLocalDateTime()));
                invoice.setTotalAmount(rs.getFloat("total_amount"));
                invoice.setProductName(rs.getString("product_name"));
                invoice.setQuantity(rs.getInt("quantity"));
                invoices.add(invoice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return invoices;
    }

    @Override
    public int addInvoice(Invoice invoice) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{CALL add_invoice(?,?)}");
            callSt.setInt(1, invoice.getCustomerId());
            callSt.registerOutParameter(2, Types.INTEGER);
            callSt.execute();



            int generatedId = callSt.getInt(2);
            return generatedId ; // trả về id hoá đơn vừa thêm
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return-1; // nếu lỗi thì trả về null
    }

    @Override
    public List<Invoice> findInvoiceByCustomerName(String customerName) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Invoice> invoiceList = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_invoice_by_name(?)}");
            callSt.setString(1, customerName);
            ResultSet rs = callSt.executeQuery();
            invoiceList = new ArrayList<>();
            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setInvoiceId(rs.getInt("invoice_id"));
                invoice.setCustomerId(rs.getInt("customer_id"));
                invoice.setCreatedAt(LocalDate.from(rs.getTimestamp("created_at").toLocalDateTime()));
                invoice.setCustomerName(rs.getString("customer_name"));
                invoice.setTotalAmount(rs.getFloat("total_amount"));
                invoiceList.add(invoice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return  invoiceList;
    }

    @Override
    public List<Invoice> findInvoiceByCustomerDate(LocalDate date) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Invoice> invoiceList = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_invoice_by_date(?)}");
            callSt.setDate(1,Date.valueOf(date));
            ResultSet rs = callSt.executeQuery();
            invoiceList = new ArrayList<>();
            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setInvoiceId(rs.getInt("invoice_id"));
                invoice.setCustomerId(rs.getInt("customer_id"));
                invoice.setCreatedAt(LocalDate.from(rs.getTimestamp("created_at").toLocalDateTime()));
                invoice.setCustomerName(rs.getString("customer_name"));
                invoice.setTotalAmount(rs.getFloat("total_amount"));
                invoiceList.add(invoice);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return invoiceList;
    }

    @Override
    public boolean updateInvoice(int invoiceId,float totalAmount) {
        Connection conn = null;
        CallableStatement callSt = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_total_amount(?,?)}");
            callSt.setInt(1, invoiceId);
            callSt.setFloat(2, totalAmount);
            callSt.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return false ;
    }

    @Override
    public void getRevenueByDay(LocalDate date) {
        Connection conn = null;
        CallableStatement callSt = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call revenue_by_day(?)}");
            callSt.setDate(1,Date.valueOf(date));
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                System.out.println("Ngày: " + rs.getDate("created_at") +
                        ", Tổng doanh thu: " + rs.getFloat("total_revenue"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
    }

    @Override
    public void getRevenueByMonth(int month, int year) {
        Connection conn = null;
        CallableStatement callSt = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call revenue_by_month(?,?)}");
            callSt.setInt(1, month);
            callSt.setInt(2, year);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                System.out.println("Tháng: " + rs.getInt("month") +
                        "/" + rs.getInt("year") +
                        ", Tổng doanh thu: " + rs.getDouble("total_revenue"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
    }

    @Override
    public void getRevenueByYear(int year) {
        Connection conn = null;
        CallableStatement callSt = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call revenue_by_year(?)}");
            callSt.setInt(1,year);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                System.out.println("Năm: " + rs.getInt("year") +
                        ", Tổng doanh thu: " + rs.getDouble("total_revenue"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
    }


}
