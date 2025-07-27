package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Invoice {
    private int invoiceId;
    private int customerId;
    private String customerName;
    private LocalDate createdAt;
    private float totalAmount;
    private String productName;
    private int quantity;
    public Invoice() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Invoice(int invoiceId, int customerId, String customerName, LocalDate createdAt, float totalAmount, String productName, int quantity) {
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.createdAt = createdAt;
        this.totalAmount = totalAmount;
        this.productName = productName;
        this.quantity = quantity;
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
                "| %-10d | %-13d | %-14s | %-15s  | %,-16.2f VND | %-20s | %-12d |",
                this.invoiceId,
                this.customerId,
                this.customerName,
                createdAtStr,
                this.totalAmount,
                this.productName,
                this.quantity
        );
    }
}
