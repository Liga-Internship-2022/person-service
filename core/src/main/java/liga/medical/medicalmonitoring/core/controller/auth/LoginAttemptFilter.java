package liga.medical.medicalmonitoring.core.controller.auth;

import liga.medical.medicalmonitoring.core.mapping.LoginAttemptMapper;
import liga.medical.medicalmonitoring.core.model.LoginAttempt;
import liga.medical.medicalmonitoring.dto.auth.LoginAttemptDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

@Slf4j
public class LoginAttemptFilter extends UsernamePasswordAuthenticationFilter {

    private final LoginAttemptMapper loginAttemptMapper;
    private final ModelMapper modelMapper;

    public LoginAttemptFilter(AuthenticationManager authenticationManager,
                              LoginAttemptMapper loginAttemptMapper,
                              ModelMapper modelMapper) {
        super.setAuthenticationManager(authenticationManager);
        this.loginAttemptMapper = loginAttemptMapper;
        this.modelMapper = modelMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String logId = "auth-logger";
        String username = request.getParameter("username");
        log.info("[{}] попытка выполнить вход в систему под username={}", logId, username);
        LoginAttemptDto loginAttempt = LoginAttemptDto.builder()
                .dttm(new Timestamp(System.currentTimeMillis()))
                .username(username)
                .build();
        loginAttemptMapper.save(modelMapper.map(loginAttempt, LoginAttempt.class));

        return super.attemptAuthentication(request, response);
    }
}
