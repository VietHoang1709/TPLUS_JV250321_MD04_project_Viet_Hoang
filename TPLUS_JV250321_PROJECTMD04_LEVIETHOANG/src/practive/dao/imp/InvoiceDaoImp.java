package dao.imp;

import dao.InvoiceDAO;
import entity.Invoice;
import util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
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
    public boolean addInvoice(Invoice invoice) {
        Connection conn = null;
        CallableStatement callSt = null;
        try{
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call add_invoice(?,?)}");
            callSt.setInt(1, invoice.getCustomerId());
            callSt.setFloat(2, invoice.getTotalAmount());
            callSt.executeUpdate();
            return true; // Them thanh cong
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return false; // Them that bai
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

//    @Override
//    public boolean updateInvoice(Invoice invoice) {
//        Connection conn = null;
//        CallableStatement callSt = null;
//        try{
//            conn = ConnectionDB.openConnection();
//            callSt = conn.prepareCall("{call update_invoice(?,?,?,?)}");
//            callSt.setInt(1, invoice.getInvoiceId());
//            callSt.setInt(2, invoice.getCustomerId());
//            callSt.setDate(3, Date.valueOf(invoice.getCreatedAt()));
//            callSt.setFloat(4, invoice.getTotalAmount());
//            callSt.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            ConnectionDB.closeConnection(conn,callSt);
//        }
//        return false;
//    }

}
