package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Invoice {
    private int invoiceId;
    private int customerId;
    private String customerName;
    private LocalDate createdAt;
    private float totalAmount;
    public Invoice() {
    }
    public Invoice(int invoiceId, int customerId, String customerName, LocalDate createdAt, float totalAmount) {
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.createdAt = createdAt;
        this.totalAmount = totalAmount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String createdAtStr = (this.createdAt != null) ? this.createdAt.format(formatter) : "N/A";

        return String.format(
                "%-10d | %-10d | %-20s | %-12s | %,15.2f VND",
                this.invoiceId,
                this.customerId,
                this.customerName,
                createdAtStr,
                this.totalAmount
        );
    }
}
