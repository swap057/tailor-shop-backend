package in.sp.tailor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Endpoints the login screen uses. These are always reachable (not gated),
 * so the frontend can find out whether a passcode is needed and verify it.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${APP_PASSCODE:}")
    private String appPasscode;

    private boolean enabled() {
        return appPasscode != null && !appPasscode.isBlank();
    }

    /** Tells the frontend whether a passcode is required at all. */
    @GetMapping("/status")
    public Map<String, Boolean> status() {
        Map<String, Boolean> m = new HashMap<>();
        m.put("required", enabled());
        return m;
    }

    /** Validates a passcode entered on the login screen. */
    @PostMapping("/verify")
    public ResponseEntity<Map<String, Object>> verify(
            @RequestBody(required = false) Map<String, String> body,
            @RequestHeader(value = "X-App-Key", required = false) String header) {

        Map<String, Object> res = new HashMap<>();

        // If protection is off, anything is accepted
        if (!enabled()) {
            res.put("ok", true);
            return ResponseEntity.ok(res);
        }

        String provided = header;
        if ((provided == null || provided.isBlank()) && body != null) {
            provided = body.get("passcode");
        }

        if (appPasscode.equals(provided)) {
            res.put("ok", true);
            return ResponseEntity.ok(res);
        }

        res.put("ok", false);
        res.put("message", "Invalid passcode");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
    }
}
