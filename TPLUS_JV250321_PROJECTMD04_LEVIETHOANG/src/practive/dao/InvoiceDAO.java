package dao;

import entity.Invoice;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceDAO {
    List<Invoice> getAllInvoices();
    int addInvoice(Invoice invoice);
    List<Invoice> findInvoiceByCustomerName(String customerName);
    List<Invoice> findInvoiceByCustomerDate(LocalDate date);
    boolean updateInvoice(int invoiceId,float totalAmount);
    void getRevenueByDay(LocalDate date);
    void getRevenueByMonth(int month, int year);
    void getRevenueByYear(int year);
}
