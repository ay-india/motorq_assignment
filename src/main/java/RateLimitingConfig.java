import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Configuration
public class RateLimitingConfig implements WebMvcConfigurer {

    private static final int MAX_REQUESTS_PER_MINUTE = 60; // Adjust as needed

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RateLimitingInterceptor(MAX_REQUESTS_PER_MINUTE))
                .addPathPatterns("/api/**"); // Apply rate limiting to specific endpoints
    }

    private static class RateLimitingInterceptor implements HandlerInterceptor {

        private final int maxRequests;
        private final TimeUnit timeUnit = TimeUnit.MINUTES;
        private final RateLimiter rateLimiter;

        public RateLimitingInterceptor(int maxRequests) {
            this.maxRequests = maxRequests;
            this.rateLimiter = RateLimiter.create(maxRequests, timeUnit);
        }

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            if (rateLimiter.tryAcquire()) {
                return true; // Allow the request to proceed
            } else {
                response.setStatus(HttpServletResponse.SC_TOO_MANY_REQUESTS);
                response.getWriter().write("Rate limit exceeded.");
                return false; // Block the request
            }
        }
    }
}
