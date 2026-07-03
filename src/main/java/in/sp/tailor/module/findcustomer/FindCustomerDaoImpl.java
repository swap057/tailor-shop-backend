package in.sp.tailor.module.findcustomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class FindCustomerDaoImpl implements FindCustomerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<CustomerProfileDto> getAllCustomersWithDetails() {
        try {
            // 1. Fetch All Customers
            String customerSql = "SELECT customer_id, full_name, mobile_no, address FROM customers WHERE is_active = 1 ORDER BY full_name ASC";
            
            List<CustomerProfileDto> customers = jdbcTemplate.query(customerSql, (rs, rowNum) -> {
                CustomerProfileDto dto = new CustomerProfileDto();
                dto.setCustomerId(rs.getInt("customer_id"));
                dto.setFullName(rs.getString("full_name"));
                dto.setMobileNo(rs.getString("mobile_no"));
                dto.setAddress(rs.getString("address"));
                return dto;
            });

            // 2. Fetch All Orders (Sorted by Newest First)
            String orderSql = "SELECT order_id, customer_id, order_date, status, shirt_qty, pant_qty, " +
                    "shirt_length, shirt_front, shirt_shoulder, shirt_sleeve, shirt_half_sleeve, shirt_collar, shirt_chest, " +
                    "pant_length, pant_below_waist, pant_waist, pant_thigh, pant_knee, pant_bottom " +
                    "FROM orders ORDER BY order_date DESC";

            List<Map<String, Object>> allOrders = jdbcTemplate.queryForList(orderSql);

            // 3. Match Orders to Customers
            for (CustomerProfileDto customer : customers) {
                int cid = customer.getCustomerId();
                
                List<OrderHistoryDto> historyList = new ArrayList<>();
                MeasurementsDto latestMeasurements = null;
                String lastOrderDateStr = null;

                for (Map<String, Object> row : allOrders) {
                    
                    // TYPE-SAFE CASTING: Prevents crashes if MySQL sends a Long instead of Integer
                    Number dbCustomerIdObj = (Number) row.get("customer_id");
                    
                    if (dbCustomerIdObj != null && dbCustomerIdObj.intValue() == cid) {
                        
                        // Safely extract the date (e.g., "2026-05-08")
                        String dateStr = row.get("order_date") != null ? row.get("order_date").toString().split(" ")[0] : "";

                        // Because orders are sorted DESC, the first one we find is the latest date!
                        if (lastOrderDateStr == null) {
                            lastOrderDateStr = dateStr; 
                        }

                        // Build the Order History item (TYPE-SAFE)
                        OrderHistoryDto historyDto = new OrderHistoryDto();
                        historyDto.setOrderId(((Number) row.get("order_id")).intValue());
                        historyDto.setDate(dateStr);
                        historyDto.setStatus((String) row.get("status"));
                        
                        int sQty = row.get("shirt_qty") != null ? ((Number) row.get("shirt_qty")).intValue() : 0;
                        int pQty = row.get("pant_qty") != null ? ((Number) row.get("pant_qty")).intValue() : 0;
                        
                        String itemsStr = "";
                        if (sQty > 0) itemsStr += sQty + " Shirts";
                        if (pQty > 0) itemsStr += (sQty > 0 ? ", " : "") + pQty + " Pants";
                        if (itemsStr.isEmpty()) itemsStr = "No Items";
                        
                        historyDto.setItems(itemsStr.trim());
                        historyList.add(historyDto);

                        // Grab the measurements ONLY from their most recent order
                        if (latestMeasurements == null) {
                            latestMeasurements = new MeasurementsDto();
                            
                            // Shirt Measurements
                            latestMeasurements.setShirtLength((String) row.get("shirt_length"));
                            latestMeasurements.setShirtCollar((String) row.get("shirt_collar"));
                            latestMeasurements.setShirtFront(row.get("shirt_front") != null ? ((Number) row.get("shirt_front")).doubleValue() : 0);
                            latestMeasurements.setShirtShoulder(row.get("shirt_shoulder") != null ? ((Number) row.get("shirt_shoulder")).doubleValue() : 0);
                            latestMeasurements.setShirtSleeve(row.get("shirt_sleeve") != null ? ((Number) row.get("shirt_sleeve")).doubleValue() : 0);
                            Object halfSleeveVal = row.get("shirt_half_sleeve");
                            latestMeasurements.setShirtHalfSleeve(halfSleeveVal != null ? halfSleeveVal.toString() : "");
                            latestMeasurements.setShirtChest(row.get("shirt_chest") != null ? ((Number) row.get("shirt_chest")).doubleValue() : 0);
                            
                            // Pant Measurements
                            latestMeasurements.setPantLength((String) row.get("pant_length"));
                            latestMeasurements.setPantBottom((String) row.get("pant_bottom"));
                            latestMeasurements.setPantBelowWaist(row.get("pant_below_waist") != null ? ((Number) row.get("pant_below_waist")).doubleValue() : 0);
                            latestMeasurements.setPantWaist(row.get("pant_waist") != null ? ((Number) row.get("pant_waist")).doubleValue() : 0);
                            latestMeasurements.setPantThigh(row.get("pant_thigh") != null ? ((Number) row.get("pant_thigh")).doubleValue() : 0);
                            latestMeasurements.setPantKnee(row.get("pant_knee") != null ? ((Number) row.get("pant_knee")).doubleValue() : 0);
                        }
                    }
                }

                // Attach the compiled data to the customer
                customer.setHistory(historyList);
                customer.setMeasurements(latestMeasurements); 
                customer.setLastOrderDate(lastOrderDateStr);
            }

            return customers;

        } catch (Exception e) {
            throwWrapped(e);
            return null; // unreachable
        }
    }

    @Override
    public List<CustomerProfileDto> getHiddenCustomers() {
        String sql = "SELECT customer_id, full_name, mobile_no, address FROM customers WHERE is_active = 0 ORDER BY full_name ASC";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            CustomerProfileDto dto = new CustomerProfileDto();
            dto.setCustomerId(rs.getInt("customer_id"));
            dto.setFullName(rs.getString("full_name"));
            dto.setMobileNo(rs.getString("mobile_no"));
            dto.setAddress(rs.getString("address"));
            return dto;
        });
    }

    @Override
    public boolean setCustomerActive(int customerId, boolean active) {
        String sql = "UPDATE customers SET is_active = ? WHERE customer_id = ?";
        int rows = jdbcTemplate.update(sql, active ? 1 : 0, customerId);
        return rows > 0;
    }

    private void throwWrapped(Exception e) {
        // Prints the exact reason in the server console, then surfaces a clean message
        System.err.println("CRITICAL ERROR IN FindCustomerDaoImpl:");
        e.printStackTrace();
        throw new RuntimeException("Error fetching customer data: " + e.getMessage());
    }
}