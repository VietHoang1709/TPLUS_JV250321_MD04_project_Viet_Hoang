package business.imp;

import business.InvoiceDetailsBusiness;
import dao.InvoiceDetailsDAO;
import dao.imp.InvoiceDetailsImp;
import entity.InvoiceDetails;

public class InvoiceDetailsBusinessImp implements InvoiceDetailsBusiness {
    private final InvoiceDetailsDAO invoiceDetailsDAO;

    public InvoiceDetailsBusinessImp() {
        invoiceDetailsDAO = new InvoiceDetailsImp();
    }

    @Override
    public boolean addInvoiceDetails(InvoiceDetails invoiceDetails) {
        return invoiceDetailsDAO.addInvoiceDetails(invoiceDetails);
    }
}
