package dao;

import entity.Invoice;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceDAO {
    List<Invoice> getAllInvoices();
    boolean addInvoice(Invoice invoice);
    List<Invoice> findInvoiceByCustomerName(String customerName);
    List<Invoice> findInvoiceByCustomerDate(LocalDate date);
//    boolean updateInvoice(Invoice invoice);
    void getRevenueByDay(LocalDate date);
    void getRevenueByMonth(int month, int year);
    void getRevenueByYear(int year);
}
