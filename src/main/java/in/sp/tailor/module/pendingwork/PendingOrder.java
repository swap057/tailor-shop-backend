package in.sp.tailor.module.pendingwork;

import java.sql.Date;

public class PendingOrder {
    // --- IDs ---
    private int orderId;
    private int customerId;

    // --- Customer Details ---
    private String fullName;
    private String mobileNo;
    private String photoPath;

    // --- Basic Order Details ---
    private Date deadlineDate;
    private String status;
    private String remark;
    private String shirtRemark;
    private String pantRemark;

    // --- SHIRT Data ---
    private int shirtQty;
    private int shirtCompletedQty;
    
    // Changed from double to String (to allow "32 / 40")
    private String shirtLength; 
    private String shirtCollar; 

    // Keep Body Measurements as double
    private double shirtFront;
    private double shirtShoulder;
    private double shirtSleeve;
    private double shirtChest;
    private String shirtHalfSleeve; // CHANGED to String for two values "12 / 6"
    private String shirtStyle;

    // --- PANT Data ---
    private int pantQty;
    private int pantCompletedQty;

    // Changed from double to String
    private String pantLength; 
    private String pantBottom; 

    // Keep Body Measurements as double
    private double pantBelowWaist;
    private double pantWaist;
    private double pantThigh;
    private double pantKnee;
    private String pantStyle;

    // --- GETTERS AND SETTERS ---
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getMobileNo() { return mobileNo; }
    public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }

    public String getPhotoPath() { return photoPath; }
    public void setPhotoPath(String photoPath) { this.photoPath = photoPath; }

    public Date getDeadlineDate() { return deadlineDate; }
    public void setDeadlineDate(Date deadlineDate) { this.deadlineDate = deadlineDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }

    public String getShirtRemark() { return shirtRemark; }
    public void setShirtRemark(String shirtRemark) { this.shirtRemark = shirtRemark; }

    public String getPantRemark() { return pantRemark; }
    public void setPantRemark(String pantRemark) { this.pantRemark = pantRemark; }

    // Shirt
    public int getShirtQty() { return shirtQty; }
    public void setShirtQty(int shirtQty) { this.shirtQty = shirtQty; }

    public int getShirtCompletedQty() { return shirtCompletedQty; }
    public void setShirtCompletedQty(int shirtCompletedQty) { this.shirtCompletedQty = shirtCompletedQty; }

    public String getShirtLength() { return shirtLength; }
    public void setShirtLength(String shirtLength) { this.shirtLength = shirtLength; }

    public double getShirtFront() { return shirtFront; }
    public void setShirtFront(double shirtFront) { this.shirtFront = shirtFront; }

    public double getShirtShoulder() { return shirtShoulder; }
    public void setShirtShoulder(double shirtShoulder) { this.shirtShoulder = shirtShoulder; }

    public double getShirtSleeve() { return shirtSleeve; }
    public void setShirtSleeve(double shirtSleeve) { this.shirtSleeve = shirtSleeve; }

    // <-- ADDED GETTER/SETTER
    public String getShirtHalfSleeve() { return shirtHalfSleeve; }
    public void setShirtHalfSleeve(String shirtHalfSleeve) { this.shirtHalfSleeve = shirtHalfSleeve; }

    public String getShirtCollar() { return shirtCollar; }
    public void setShirtCollar(String shirtCollar) { this.shirtCollar = shirtCollar; }

    public double getShirtChest() { return shirtChest; }
    public void setShirtChest(double shirtChest) { this.shirtChest = shirtChest; }

    public String getShirtStyle() { return shirtStyle; }
    public void setShirtStyle(String shirtStyle) { this.shirtStyle = shirtStyle; }

    // Pant
    public int getPantQty() { return pantQty; }
    public void setPantQty(int pantQty) { this.pantQty = pantQty; }

    public int getPantCompletedQty() { return pantCompletedQty; }
    public void setPantCompletedQty(int pantCompletedQty) { this.pantCompletedQty = pantCompletedQty; }

    public String getPantLength() { return pantLength; }
    public void setPantLength(String pantLength) { this.pantLength = pantLength; }

    public double getPantBelowWaist() { return pantBelowWaist; }
    public void setPantBelowWaist(double pantBelowWaist) { this.pantBelowWaist = pantBelowWaist; }

    public double getPantWaist() { return pantWaist; }
    public void setPantWaist(double pantWaist) { this.pantWaist = pantWaist; }

    public double getPantThigh() { return pantThigh; }
    public void setPantThigh(double pantThigh) { this.pantThigh = pantThigh; }

    public double getPantKnee() { return pantKnee; }
    public void setPantKnee(double pantKnee) { this.pantKnee = pantKnee; }

    public String getPantBottom() { return pantBottom; }
    public void setPantBottom(String pantBottom) { this.pantBottom = pantBottom; }

    public String getPantStyle() { return pantStyle; }
    public void setPantStyle(String pantStyle) { this.pantStyle = pantStyle; }
}