package entity;

import java.time.LocalDate;

public class Invoice {
    private int invoiceId;
    private int customerId;
    private LocalDate createdAt;
    private float totalAmount;

    public Invoice() {
    }

    public Invoice(int invoiceId, int customerId, LocalDate createdAt, float totalAmount) {
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.createdAt = createdAt;
        this.totalAmount = totalAmount;
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", customerId=" + customerId +
                ", createdAt=" + createdAt +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
