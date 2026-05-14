package in.sp.tailor.module.addcustomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AddCustomerDaoImpl implements AddCustomerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int saveCustomer(Customer c) {
        String checkSql = "SELECT customer_id FROM customers WHERE mobile_no = ?";
        try {
            return jdbcTemplate.queryForObject(checkSql, Integer.class, c.getMobileNo());
        } catch (EmptyResultDataAccessException e) {
            String sql = "INSERT INTO customers (full_name, mobile_no, address, photo_path, created_at) VALUES (?, ?, ?, ?, NOW())";
            jdbcTemplate.update(sql, c.getFullName(), c.getMobileNo(), c.getAddress(), c.getPhotoPath());
            
            return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        }
    }

    @Override
    public int saveOrder(ShopOrder o) {
        String shirtStatus = (o.getShirtQty() > 0) ? "PENDING" : "NA";
        String pantStatus = (o.getPantQty() > 0) ? "PENDING" : "NA";

        // ADDED shirt_half_sleeve and one extra '?' parameter
        String sql = "INSERT INTO orders (" +
                "customer_id, order_date, deadline_date, language_req, status, " +
                "shirt_length, shirt_chest, shirt_front, shirt_shoulder, shirt_sleeve, shirt_half_sleeve, shirt_collar, shirt_style, shirt_qty, shirt_status, " +
                "pant_length, pant_waist, pant_thigh, pant_knee, pant_bottom, pant_below_waist, pant_style, pant_qty, pant_status, " +
                "remark" +
                ") VALUES (?, NOW(), ?, ?, 'PENDING', ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql, 
                o.getCustomerId(), 
                o.getDeadlineDate(), 
                o.getLanguageReq(),
                
                // Shirt Params (ADDED o.getShirtHalfSleeve())
                o.getShirtLength(), o.getShirtChest(), o.getShirtFront(), o.getShirtShoulder(),
                o.getShirtSleeve(), o.getShirtHalfSleeve(), o.getShirtCollar(), o.getShirtStyle(), o.getShirtQty(), shirtStatus,
                
                // Pant Params
                o.getPantLength(), o.getPantWaist(), o.getPantThigh(), o.getPantKnee(),
                o.getPantBottom(), o.getPantBelowWaist(), o.getPantStyle(), o.getPantQty(), pantStatus,
                
                // Remark
                o.getRemark()
        );
    }
}