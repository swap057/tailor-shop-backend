package in.sp.tailor.module.findcustomer;

public class OrderHistoryDto {
    private int orderId;
    private String date; // E.g., "2026-04-17"
    private String items; // E.g., "3 Shirts, 1 Pant"
    private String status; // E.g., "PENDING" or "COMPLETED"

    // Getters and Setters
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getItems() { return items; }
    public void setItems(String items) { this.items = items; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}