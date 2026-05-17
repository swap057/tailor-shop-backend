package in.sp.tailor.module.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
 
public class DashboardController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/dashboard-stats")
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        
        // 1. Total Pending (Strictly Active Orders only)
        // Matches the Pending Work table (Hides Completed items)
        String sqlPending = "SELECT COUNT(*) FROM orders WHERE status = 'PENDING'";
        int pending = jdbcTemplate.queryForObject(sqlPending, Integer.class);
        
        // 2. Urgent (Deadline is Today OR Past, AND work is still PENDING)
        // Logic: Only counts items that need stitching. If it is COMPLETED, it is ignored.
        String sqlUrgent = "SELECT COUNT(*) FROM orders WHERE deadline_date <= CURDATE() AND status = 'PENDING'";
        int urgent = jdbcTemplate.queryForObject(sqlUrgent, Integer.class);
        
        // 3. Completed (Orders ready for trial)
        String sqlReady = "SELECT COUNT(*) FROM orders WHERE status = 'COMPLETED'";
        int ready = jdbcTemplate.queryForObject(sqlReady, Integer.class);

        // 4. Total Customers
        String sqlCustomers = "SELECT COUNT(*) FROM customers";
        int totalCustomers = jdbcTemplate.queryForObject(sqlCustomers, Integer.class);

        // 5. Graph Data (Completed Orders per Day)
        String sqlGraph = "SELECT completed_date as date, COUNT(*) as count " +
                          "FROM orders " +
                          "WHERE status = 'COMPLETED' AND completed_date IS NOT NULL " +
                          "GROUP BY completed_date " +
                          "ORDER BY completed_date ASC LIMIT 7";
        List<Map<String, Object>> graphData = jdbcTemplate.queryForList(sqlGraph);

        stats.put("pending", pending);
        stats.put("urgent", urgent);
        stats.put("ready", ready);
        stats.put("totalCustomers", totalCustomers);
        stats.put("graphData", graphData); 
        
        return stats;
    }
}