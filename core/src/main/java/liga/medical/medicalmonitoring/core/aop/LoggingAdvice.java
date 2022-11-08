package liga.medical.medicalmonitoring.core.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.service.LoggingService;
import lombok.RequiredArgsConstructor;
import model.LogMessage;
import model.SystemType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAdvice {

    private final LoggingService loggingService;

    @Pointcut(value = "@annotation(liga.medical.medicalmonitoring.core.aop.annotations.RestLog)")
    public void restLogPointcut() {
    }

    @Before(value = "restLogPointcut()")
    public void restLogger(JoinPoint jp) throws JsonProcessingException {
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
        String className = jp.getTarget().getClass().getName();
        String methodName = jp.getSignature().getName();
        String args = objectMapper.writeValueAsString(jp.getArgs());

        LogMessage logMessage = LogMessage.builder()
                .logId(logId)
                .username(username)
                .className(className)
                .methodName(methodName)
                .args(args)
                .build();
        loggingService.log(logMessage, SystemType.PERSON_SERVICE);
    }
}
