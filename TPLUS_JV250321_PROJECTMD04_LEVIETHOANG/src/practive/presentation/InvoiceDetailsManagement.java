package presentation;

import business.InvoiceDetailsBusiness;
import business.imp.InvoiceDetailsBusinessImp;

public class InvoiceDetailsManagement {
    private final InvoiceDetailsBusiness invoiceDetailsBusiness;

    public InvoiceDetailsManagement() {
        invoiceDetailsBusiness = new InvoiceDetailsBusinessImp();
    }
}
