package pl.reskilled.menteeManagerMicroservices.user.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.reskilled.menteeManagerMicroservices.user.security.jwt.security.jwt.AuthEntryPointJwt;
import pl.reskilled.menteeManagerMicroservices.user.security.jwt.security.jwt.AuthTokenFilter;
import pl.reskilled.menteeManagerMicroservices.user.security.jwt.security.jwt.JwtUtils;
import pl.reskilled.menteeManagerMicroservices.user.security.jwt.security.service.MongoDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        //securedEnabled = true,
        // jsr250Enabled = true)
        prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MongoDetailsServiceImpl mongoDetailsService;
    private final AuthEntryPointJwt unauthorizedHandler;
    private final JwtUtils utils;


    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/webjars/**",
            "/csrf", "/",
            "/login**",
            "/api/signin**",
            "/api/projects/add",
            "/api/projects",
            "/api/csv/project"
    };

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter(utils, mongoDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
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
        http.cors().and().csrf().disable();
        http.authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("api/students").hasAnyRole("STUDENT", "MENTOR")
                .antMatchers("api/students/add").hasAnyRole("STUDENT", "MENTOR")
                .antMatchers("api/refreshtoken").hasAnyRole("STUDENT", "MENTOR")
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .headers().frameOptions().disable();
        http.httpBasic().disable();
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
