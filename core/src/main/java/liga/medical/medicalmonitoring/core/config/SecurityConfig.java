package liga.medical.medicalmonitoring.core.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        String userRole = "USER";
        String adminRole = "ADMIN";
        String restMedicalCardRole = "REST_MEDICAL_CARD";
        String restContactRole = "REST_CONTACT";
        String restPersonDataRole = "REST_PERSON_DATA";

        httpSecurity
                .httpBasic().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login").not().fullyAuthenticated()
                .antMatchers("/registration").not().fullyAuthenticated()
                .antMatchers("/admin/**").hasRole(adminRole)
                .antMatchers("/persons/**").hasRole(adminRole)
                .antMatchers("/medical-cards/**").hasRole(restMedicalCardRole)
                .antMatchers("/contacts/**").hasRole(restContactRole)
                .antMatchers("/person-data/**").hasRole(restPersonDataRole)
                .antMatchers("/**").hasAnyRole(userRole, adminRole)
                .anyRequest().authenticated().and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/").permitAll().and()
                .logout().permitAll()
                .logoutSuccessUrl("/login");
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
