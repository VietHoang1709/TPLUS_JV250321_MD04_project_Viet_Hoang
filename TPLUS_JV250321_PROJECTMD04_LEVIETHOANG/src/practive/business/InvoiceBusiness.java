package business;

import entity.Invoice;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceBusiness {
    List<Invoice> getInvoices();
    int addInvoice(Invoice invoice);
    boolean updateInvoice(int invoiceId,float totalAmount);
    List<Invoice> getInvoicesByCustomerName(String customerName);
    List<Invoice> getInvoicesByCreatedAt(LocalDate createdAt);
    void getAllRevenueByDay(LocalDate date);
    void getAllRevenueByMonth(int month, int year);
    void getAllRevenueByYear(int year);
}
