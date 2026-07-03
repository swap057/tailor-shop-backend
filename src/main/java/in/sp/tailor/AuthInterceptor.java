package in.sp.tailor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Simple shared-passcode gate. Every API request must carry the correct
 * X-App-Key header, checked against the APP_PASSCODE environment variable.
 *
 * If APP_PASSCODE is blank / not set, protection is OFF (fail-open) so the app
 * keeps working normally until you choose to enable it by setting the variable.
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Value("${APP_PASSCODE:}")
    private String appPasscode;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Protection disabled when no passcode configured
        if (appPasscode == null || appPasscode.isBlank()) {
            return true;
        }
        // Always allow CORS pre-flight requests
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        // Always allow the auth endpoints (status/verify) so the login screen works
        String path = request.getRequestURI();
        if (path != null && path.startsWith("/auth/")) {
            return true;
        }
        // Everything else needs the correct passcode header
        String key = request.getHeader("X-App-Key");
        if (appPasscode.equals(key)) {
            return true;
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"status\":\"Unauthorized\",\"message\":\"Invalid or missing passcode\"}");
        return false;
    }
}
