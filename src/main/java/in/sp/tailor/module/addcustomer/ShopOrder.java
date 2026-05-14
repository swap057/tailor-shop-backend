package in.sp.tailor.module.addcustomer;

import java.sql.Date;
import java.time.LocalDateTime;

public class ShopOrder {
    
    private int orderId;
    private int customerId;
    
    // --- BASIC ORDER INFO ---
    private LocalDateTime orderDate;
    private Date deadlineDate;
    private String languageReq;
    private String status; // Overall Order Status (e.g., PENDING, COMPLETED)

    // --- SHIRT DETAILS ---
    private String shirtLength;       // CHANGED to String for "Reg: 32, Short: 31"
    private double shirtChest;
    private double shirtFront;
    private double shirtShoulder;
    private double shirtSleeve;
    private double shirtHalfSleeve;   // NEW FIELD ADDED
    private String shirtCollar;       // CHANGED to String for "15 / 9.5"
    private String shirtStyle;
    private int shirtQty; 
    private String shirtStatus;       // For tracking shirt progress separately

    // --- PANT DETAILS ---
    private double pantLength;
    private double pantWaist;
    private double pantThigh;
    private double pantKnee;
    private double pantBottom;
    private double pantBelowWaist;
    private String pantStyle;
    private int pantQty;
    private String pantStatus;        // For tracking pant progress separately

    // --- EXTRAS ---
    private String remark;            // For special instructions

    // ============================
    //    GETTERS AND SETTERS
    // ============================

    // ID & Customer
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    
    // Basic Info
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
    
    public Date getDeadlineDate() { return deadlineDate; }
    public void setDeadlineDate(Date deadlineDate) { this.deadlineDate = deadlineDate; }
    
    public String getLanguageReq() { return languageReq; }
    public void setLanguageReq(String languageReq) { this.languageReq = languageReq; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    // --- Shirt Fields ---
    public String getShirtLength() { return shirtLength; }
    public void setShirtLength(String shirtLength) { this.shirtLength = shirtLength; }
    
    public double getShirtChest() { return shirtChest; }
    public void setShirtChest(double shirtChest) { this.shirtChest = shirtChest; }
    
    public double getShirtFront() { return shirtFront; }
    public void setShirtFront(double shirtFront) { this.shirtFront = shirtFront; }
    
    public double getShirtShoulder() { return shirtShoulder; }
    public void setShirtShoulder(double shirtShoulder) { this.shirtShoulder = shirtShoulder; }
    
    public double getShirtSleeve() { return shirtSleeve; }
    public void setShirtSleeve(double shirtSleeve) { this.shirtSleeve = shirtSleeve; }

    public double getShirtHalfSleeve() { return shirtHalfSleeve; }
    public void setShirtHalfSleeve(double shirtHalfSleeve) { this.shirtHalfSleeve = shirtHalfSleeve; }
    
    public String getShirtCollar() { return shirtCollar; }
    public void setShirtCollar(String shirtCollar) { this.shirtCollar = shirtCollar; }
    
    public String getShirtStyle() { return shirtStyle; }
    public void setShirtStyle(String shirtStyle) { this.shirtStyle = shirtStyle; }

    public int getShirtQty() { return shirtQty; }
    public void setShirtQty(int shirtQty) { this.shirtQty = shirtQty; }

    public String getShirtStatus() { return shirtStatus; }
    public void setShirtStatus(String shirtStatus) { this.shirtStatus = shirtStatus; }
    
    // --- Pant Fields ---
    public double getPantLength() { return pantLength; }
    public void setPantLength(double pantLength) { this.pantLength = pantLength; }
    
    public double getPantWaist() { return pantWaist; }
    public void setPantWaist(double pantWaist) { this.pantWaist = pantWaist; }
    
    public double getPantThigh() { return pantThigh; }
    public void setPantThigh(double pantThigh) { this.pantThigh = pantThigh; }
    
    public double getPantKnee() { return pantKnee; }
    public void setPantKnee(double pantKnee) { this.pantKnee = pantKnee; }
    
    public double getPantBottom() { return pantBottom; }
    public void setPantBottom(double pantBottom) { this.pantBottom = pantBottom; }
    
    public double getPantBelowWaist() { return pantBelowWaist; }
    public void setPantBelowWaist(double pantBelowWaist) { this.pantBelowWaist = pantBelowWaist; }
    
    public String getPantStyle() { return pantStyle; }
    public void setPantStyle(String pantStyle) { this.pantStyle = pantStyle; }

    public int getPantQty() { return pantQty; }
    public void setPantQty(int pantQty) { this.pantQty = pantQty; }

    public String getPantStatus() { return pantStatus; }
    public void setPantStatus(String pantStatus) { this.pantStatus = pantStatus; }

    // --- Remarks ---
    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
}