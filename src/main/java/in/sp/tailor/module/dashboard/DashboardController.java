package in.sp.tailor.module.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DashboardController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/dashboard-stats")
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();

        // 1. Total Pending
        String sqlPending = "SELECT COUNT(*) FROM orders WHERE status = 'PENDING'";
        int pending = jdbcTemplate.queryForObject(sqlPending, Integer.class);

        // 2. Urgent = pending orders due TODAY (same rule as the "Urgent (Today)" tab)
        String sqlUrgent = "SELECT COUNT(*) FROM orders WHERE deadline_date = CURDATE() AND status = 'PENDING'";
        int urgent = jdbcTemplate.queryForObject(sqlUrgent, Integer.class);

        // 3. Overdue = pending orders whose delivery date has already passed
        String sqlOverdue = "SELECT COUNT(*) FROM orders WHERE deadline_date < CURDATE() AND status = 'PENDING'";
        int overdue = jdbcTemplate.queryForObject(sqlOverdue, Integer.class);

        // 4. Completed
        String sqlReady = "SELECT COUNT(*) FROM orders WHERE status = 'COMPLETED'";
        int ready = jdbcTemplate.queryForObject(sqlReady, Integer.class);

        // 5. Total Customers (only active ones, same as the Customer Directory)
        String sqlCustomers = "SELECT COUNT(*) FROM customers WHERE is_active = 1";
        int totalCustomers = jdbcTemplate.queryForObject(sqlCustomers, Integer.class);

        // 6. Graph: orders completed on each of the last 7 days (days with none show 0)
        LocalDate today = LocalDate.now(java.time.ZoneId.of("Asia/Kolkata"));
        LocalDate start = today.minusDays(6);
        String sqlGraph = "SELECT DATE_FORMAT(completed_date, '%Y-%m-%d') AS d, COUNT(*) AS c " +
                          "FROM orders WHERE status = 'COMPLETED' AND completed_date BETWEEN ? AND ? " +
                          "GROUP BY completed_date";
        Map<String, Integer> counts = new LinkedHashMap<>();
        for (Map<String, Object> row : jdbcTemplate.queryForList(sqlGraph, start.toString(), today.toString())) {
            counts.put(String.valueOf(row.get("d")), ((Number) row.get("c")).intValue());
        }
        DateTimeFormatter label = DateTimeFormatter.ofPattern("dd MMM");
        List<Map<String, Object>> graphData = new ArrayList<>();
        for (LocalDate day = start; !day.isAfter(today); day = day.plusDays(1)) {
            Map<String, Object> point = new HashMap<>();
            point.put("date", day.format(label));
            point.put("count", counts.getOrDefault(day.toString(), 0));
            graphData.add(point);
        }

        stats.put("pending", pending);
        stats.put("urgent", urgent);
        stats.put("overdue", overdue);
        stats.put("ready", ready);
        stats.put("totalCustomers", totalCustomers);
        stats.put("graphData", graphData);

        return stats;
    }
}
