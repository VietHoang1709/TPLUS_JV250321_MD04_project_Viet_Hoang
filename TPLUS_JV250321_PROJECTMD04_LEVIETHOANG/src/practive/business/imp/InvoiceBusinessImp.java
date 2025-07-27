package business.imp;

import business.InvoiceBusiness;
import dao.InvoiceDAO;
import dao.imp.InvoiceDaoImp;
import entity.Invoice;

import java.time.LocalDate;
import java.util.List;

public class InvoiceBusinessImp implements InvoiceBusiness {
    private InvoiceDAO invoiceDAO;

    public InvoiceBusinessImp() {
        invoiceDAO = new InvoiceDaoImp();
    }

    @Override
    public List<Invoice> getInvoices() {
        return invoiceDAO.getAllInvoices();
    }

    @Override
    public int addInvoice(Invoice invoice) {
        return invoiceDAO.addInvoice(invoice);
    }

    @Override
    public boolean updateInvoice(int  invoiceId, float totalAmount) {
        return invoiceDAO.updateInvoice(invoiceId,totalAmount);
    }

    @Override
    public List<Invoice> getInvoicesByCustomerName(String customerName) {
        return invoiceDAO.findInvoiceByCustomerName(customerName);
    }

    @Override
    public List<Invoice> getInvoicesByCreatedAt(LocalDate createdAt) {
        return invoiceDAO.findInvoiceByCustomerDate(createdAt);
    }

    @Override
    public void getAllRevenueByDay(LocalDate date) {
       invoiceDAO.getRevenueByDay(date);
    }

    @Override
    public void getAllRevenueByMonth(int month, int year) {
        invoiceDAO.getRevenueByMonth(month, year);
    }

    @Override
    public void getAllRevenueByYear(int year) {
        invoiceDAO.getRevenueByYear(year);
    }
}
