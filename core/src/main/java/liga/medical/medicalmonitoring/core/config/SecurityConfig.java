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
        httpSecurity
                .httpBasic().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login").not().fullyAuthenticated()
                .antMatchers("/registration").not().fullyAuthenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/**").hasAnyRole("USER", "ADMIN")
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
