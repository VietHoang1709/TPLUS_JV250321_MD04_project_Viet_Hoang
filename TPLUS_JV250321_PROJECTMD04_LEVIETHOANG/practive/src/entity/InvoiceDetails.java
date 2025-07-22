package entity;

public class InvoiceDetails {
    private int invoiceDetailId;
    private int invoiceId;
    private int customerId;
    private int quantity;
    private float unitPrice;

    public InvoiceDetails() {
    }

    public InvoiceDetails(int invoiceDetailId, int invoiceId, int customerId, int quantity, float unitPrice) {
        this.invoiceDetailId = invoiceDetailId;
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getInvoiceDetailId() {
        return invoiceDetailId;
    }

    public void setInvoiceDetailId(int invoiceDetailId) {
        this.invoiceDetailId = invoiceDetailId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "InvoiceDetails{" +
                "invoiceDetailId=" + invoiceDetailId +
                ", invoiceId=" + invoiceId +
                ", customerId=" + customerId +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
