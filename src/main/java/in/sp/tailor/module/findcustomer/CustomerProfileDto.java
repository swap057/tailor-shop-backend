package in.sp.tailor.module.findcustomer;

import java.util.List;

public class CustomerProfileDto {
    private int customerId;
    private String fullName;
    private String mobileNo;
    private String address;
    private String lastOrderDate;
    
    // Nested Objects
    private MeasurementsDto measurements;
    private List<OrderHistoryDto> history;

    // Getters and Setters
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getMobileNo() { return mobileNo; }
    public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getLastOrderDate() { return lastOrderDate; }
    public void setLastOrderDate(String lastOrderDate) { this.lastOrderDate = lastOrderDate; }

    public MeasurementsDto getMeasurements() { return measurements; }
    public void setMeasurements(MeasurementsDto measurements) { this.measurements = measurements; }

    public List<OrderHistoryDto> getHistory() { return history; }
    public void setHistory(List<OrderHistoryDto> history) { this.history = history; }
}