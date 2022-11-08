package liga.medical.medicalmonitoring.core.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.service.LoggingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.LogMessage;
import model.SystemType;
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

    private final LoggingService loggingService;

    @Pointcut(value = "@annotation(liga.medical.medicalmonitoring.core.aop.annotations.RestLog)")
    public void restLogPointcut() {
    }

    @Around(value = "restLogPointcut()")
    public Object restLogger(ProceedingJoinPoint pjp) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
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

        LogMessage logMessage = LogMessage.builder()
                .logId(logId)
                .username(username)
                .className(className)
                .methodName(methodName)
                .args(args)
                .build();
        loggingService.log(logMessage, SystemType.PERSON_SERVICE);

        Object object = null;
        try {
            object = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            loggingService.logException(logMessage, SystemType.PERSON_SERVICE);
        }
        return object;
    }
}
