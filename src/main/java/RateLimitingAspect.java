import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class RateLimitingAspect {

    @Autowired
    private RedisTemplate<String, Integer> redisTemplate;

    @Around("@annotation(rateLimited)")
    public Object enforceRateLimit(ProceedingJoinPoint joinPoint, RateLimited rateLimited) throws Throwable {
        String key = rateLimited.key();
        int limit = rateLimited.limit();
        int duration = rateLimited.duration();

        Long remainingRequests = redisTemplate.opsForValue().increment(key, 1);

        if (remainingRequests != null && remainingRequests <= limit) {
            redisTemplate.expire(key, duration, TimeUnit.SECONDS);
            return joinPoint.proceed();
        } else {
            throw new RateLimitExceededException("Rate limit exceeded for key: " + key);
        }
    }
}
