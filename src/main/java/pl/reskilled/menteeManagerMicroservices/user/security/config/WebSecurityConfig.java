package pl.reskilled.menteeManagerMicroservices.user.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.reskilled.menteeManagerMicroservices.user.security.service.MongoDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MongoDetailsServiceImpl mongoDetailsService;

    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/webjars/**",
            "/csrf", "/",
            "/login**",
            "/api/signin",
            "/api/signup",
            "/api/students"
    };

    public WebSecurityConfig(MongoDetailsServiceImpl mongoDetailsService) {
        this.mongoDetailsService = mongoDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(mongoDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/api/addMentee").hasRole("STUDENT")
                .anyRequest().authenticated()
                .and()
                .headers().frameOptions().disable()
                .and()
                .formLogin().permitAll()
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error=true")
                .and()
                .rememberMe().tokenValiditySeconds(86400).rememberMeCookieName("refresh").rememberMeParameter("remember")
                .and()
                .headers().frameOptions().disable();
        http.httpBasic().disable();

    }
}
