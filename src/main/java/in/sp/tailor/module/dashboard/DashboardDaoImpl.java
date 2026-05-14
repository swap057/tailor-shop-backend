//package in.sp.tailor.module.dashboard;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Repository;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//@Repository
//public class DashboardDaoImpl implements DashboardDao {
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @Override
//    public List<DashboardOrder> getPendingOrders() {
//        String sql = "SELECT o.order_id, c.full_name, c.mobile_no, o.deadline_date, o.status, o.remark, " +
//                     "o.shirt_qty, o.shirt_status, o.shirt_style, " +
//                     "o.pant_qty, o.pant_status, o.pant_style " +
//                     "FROM orders o " +
//                     "JOIN customers c ON o.customer_id = c.customer_id " +
//                     "WHERE o.status != 'DELIVERED' " + 
//                     "ORDER BY o.deadline_date ASC";
//
//        return jdbcTemplate.query(sql, new RowMapper<DashboardOrder>() {
//            @Override
//            public DashboardOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
//                DashboardOrder d = new DashboardOrder();
//                d.setOrderId(rs.getInt("order_id"));
//                d.setCustomerName(rs.getString("full_name"));
//                d.setMobileNo(rs.getString("mobile_no"));
//                d.setDeadlineDate(rs.getDate("deadline_date"));
//                d.setStatus(rs.getString("status"));
//                d.setRemark(rs.getString("remark"));
//                
//                d.setShirtQty(rs.getInt("shirt_qty"));
//                d.setShirtStatus(rs.getString("shirt_status"));
//                d.setShirtStyle(rs.getString("shirt_style"));
//
//                d.setPantQty(rs.getInt("pant_qty"));
//                d.setPantStatus(rs.getString("pant_status"));
//                d.setPantStyle(rs.getString("pant_style"));
//                return d;
//            }
//        });
//    }
//}