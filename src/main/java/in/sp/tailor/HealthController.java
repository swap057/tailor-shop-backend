package in.sp.tailor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * "Doorbell" page for a free ping service (e.g. cron-job.org) to visit every ~10 minutes.
 * It keeps the Render server awake and the database in use, so the shop never waits
 * for the app to wake up. It reads no shop data and stores nothing.
 */
@RestController
public class HealthController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> res = new HashMap<>();
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            res.put("status", "OK");
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.put("status", "DATABASE_NOT_REACHABLE");
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(res);
        }
    }
}
