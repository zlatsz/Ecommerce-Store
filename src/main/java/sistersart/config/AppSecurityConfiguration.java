package sistersart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/js/**", "/css/**", "/images/**").permitAll()
                .antMatchers("/api/", "/api/register", "/api/login","/api/contacts","/api/messages").anonymous()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/api/")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/api/home", true)
                .and()
                .logout()
                .logoutSuccessUrl("/api/")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/api/");
    }
}
