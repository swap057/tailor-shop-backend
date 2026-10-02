package in.sp.tailor.module.report;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Reports: all orders (with customer details and measurements) in a date range.
 * Used by the Reports page for the on-screen list, Excel and PDF.
 */
@RestController
public class ReportController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * @param from      first day, yyyy-MM-dd (included)
     * @param to        last day, yyyy-MM-dd (included)
     * @param dateBy    "order" = order (entry) date, "deadline" = delivery date
     * @param status    "ALL", "PENDING" or "COMPLETED"
     */
    @GetMapping("/reports/orders")
    public List<Map<String, Object>> ordersReport(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam(defaultValue = "order") String dateBy,
            @RequestParam(defaultValue = "ALL") String status) {

        LocalDate fromDate;
        LocalDate toDate;
        try {
            fromDate = LocalDate.parse(from);
            toDate = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Please choose valid dates.");
        }
        if (fromDate.isAfter(toDate)) {
            throw new RuntimeException("'From' date must be before 'To' date.");
        }

        String dateColumn = "deadline".equalsIgnoreCase(dateBy) ? "o.deadline_date" : "DATE(o.order_date)";

        StringBuilder sql = new StringBuilder(
            "SELECT o.order_id, " +
            "DATE_FORMAT(o.order_date, '%Y-%m-%d') AS order_date, " +
            "DATE_FORMAT(o.deadline_date, '%Y-%m-%d') AS deadline_date, " +
            "DATE_FORMAT(o.completed_date, '%Y-%m-%d') AS completed_date, " +
            "o.status, c.full_name, c.mobile_no, c.address, " +
            "o.shirt_qty, o.shirt_completed_qty, o.shirt_style, o.shirt_length, o.shirt_front, o.shirt_shoulder, " +
            "o.shirt_sleeve, o.shirt_collar, o.shirt_chest, o.shirt_pot, o.shirt_half_sleeve, o.shirt_remark, " +
            "o.pant_qty, o.pant_completed_qty, o.pant_style, o.pant_length, o.pant_below_waist, o.pant_waist, " +
            "o.pant_thigh, o.pant_knee, o.pant_bottom, o.pant_remark, o.remark " +
            "FROM orders o JOIN customers c ON o.customer_id = c.customer_id " +
            "WHERE " + dateColumn + " BETWEEN ? AND ? ");

        List<Object> params = new ArrayList<>();
        params.add(fromDate.toString());
        params.add(toDate.toString());

        if ("PENDING".equalsIgnoreCase(status) || "COMPLETED".equalsIgnoreCase(status)) {
            sql.append("AND o.status = ? ");
            params.add(status.toUpperCase());
        }

        sql.append("ORDER BY ").append(dateColumn).append(" ASC, o.order_id ASC");

        return jdbcTemplate.queryForList(sql.toString(), params.toArray());
    }
}
