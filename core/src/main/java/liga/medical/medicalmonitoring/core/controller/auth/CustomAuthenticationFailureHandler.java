package liga.medical.medicalmonitoring.core.controller.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        String logId = "login-failure-logger";
        String username = request.getParameter("username");

        log.info("[{}] неудачная попытка выполнить вход в систему. Логин: [username={}]", logId, username);

        super.onAuthenticationFailure(request, response, exception);
    }
}
