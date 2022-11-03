package liga.medical.medicalmonitoring.core.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
@RequiredArgsConstructor
public class LoggingAdvice {

    @Pointcut(value = "@annotation(liga.medical.medicalmonitoring.core.annotations.RestLog)")
    public void restLogPointcut() {
    }

    @Around(value = "restLogPointcut()")
    public Object restLogger(ProceedingJoinPoint pjp) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authName = auth.getName();
        log.info("auth.getName() = {}", authName);

        Object principal = auth.getPrincipal();

        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        String logId = "rest-logger";
        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        String args = objectMapper.writeValueAsString(pjp.getArgs());

        log.info("[{}] пользователем [{}] из класса {} вызван метод {}() с аргументами {}",
                logId, username, className, methodName, args);

        Object object = null;
        try {
            object = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        log.info("[{}] {}: {}() response -> [{}]", logId, className, methodName, objectMapper.writeValueAsString(object));
        return object;
    }
}
