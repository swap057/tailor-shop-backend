package in.sp.tailor.module.pendingwork;

import in.sp.tailor.module.pendingwork.PendingOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PendingWorkDaoImpl implements PendingWorkDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<PendingOrder> getAllPendingOrders() {
		// 🔥 UPDATED: Only show orders that are strictly 'PENDING'.
		// Completed orders will vanish from this list.
		String sql = "SELECT o.order_id, c.full_name, c.mobile_no, o.deadline_date, "
				+ "o.shirt_qty, o.shirt_completed_qty, " + "o.pant_qty, o.pant_completed_qty, o.status "
				+ "FROM orders o " + "JOIN customers c ON o.customer_id = c.customer_id "
				+ "WHERE o.status = 'PENDING' " + "ORDER BY o.deadline_date ASC";

		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(PendingOrder.class));
	}

	@Override
	public PendingOrder getOrderDetails(int orderId) {
		String sql = "SELECT o.*, c.full_name, c.photo_path FROM orders o "
				+ "JOIN customers c ON o.customer_id = c.customer_id " + "WHERE o.order_id = ?";

		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(PendingOrder.class), orderId);
	}

	@Override
	public boolean updateOrderProgress(PendingOrder order) {
		// 1. Update the counts
		String sql = "UPDATE orders SET shirt_completed_qty = ?, pant_completed_qty = ? WHERE order_id = ?";
		int rows = jdbcTemplate.update(sql, order.getShirtCompletedQty(), order.getPantCompletedQty(),
				order.getOrderId());

		// 2. Logic: Check if Fully Completed
		String checkSql = "SELECT * FROM orders WHERE order_id = ?";
		PendingOrder dbOrder = jdbcTemplate.queryForObject(checkSql, new BeanPropertyRowMapper<>(PendingOrder.class),
				order.getOrderId());

		if (dbOrder != null) {
			boolean shirtDone = dbOrder.getShirtCompletedQty() >= dbOrder.getShirtQty();
			boolean pantDone = dbOrder.getPantCompletedQty() >= dbOrder.getPantQty();
			if (shirtDone && pantDone) {
				// 👇 THIS IS THE KEY PART 👇
				jdbcTemplate.update(
						"UPDATE orders SET status = 'COMPLETED', completed_date = CURDATE() WHERE order_id = ?",
						order.getOrderId());
			} else {
				// 🔥 UPDATED: Revert to PENDING and clear the Date
				jdbcTemplate.update("UPDATE orders SET status = 'PENDING', completed_date = NULL WHERE order_id = ?",
						order.getOrderId());
			}
		}
		return rows > 0;
	}
}