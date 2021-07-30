package com.prads.bet.config.security;

import com.prads.bet.enums.RolesEnums;
import com.prads.bet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.GET, "/auth/**").hasAnyRole(RolesEnums.ROLE_USER.getRole(), RolesEnums.ROLE_BETTER.getRole(), RolesEnums.ROLE_BANK.getRole(), RolesEnums.ROLE_ADMIN.getRole(), RolesEnums.ROLE_OWNER.getRole())
                .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                .antMatchers(HttpMethod.POST, "/user").permitAll()
                .antMatchers(HttpMethod.GET, "/user/**").hasAnyRole(RolesEnums.ROLE_USER.getRole(), RolesEnums.ROLE_BETTER.getRole(), RolesEnums.ROLE_BANK.getRole(), RolesEnums.ROLE_ADMIN.getRole(), RolesEnums.ROLE_OWNER.getRole())
                .antMatchers(HttpMethod.PUT, "/user/**").hasAnyRole(RolesEnums.ROLE_USER.getRole(), RolesEnums.ROLE_BETTER.getRole(), RolesEnums.ROLE_BANK.getRole(), RolesEnums.ROLE_ADMIN.getRole(), RolesEnums.ROLE_OWNER.getRole())
                .antMatchers(HttpMethod.DELETE, "/user/**").hasAnyRole(RolesEnums.ROLE_USER.getRole(), RolesEnums.ROLE_BETTER.getRole(), RolesEnums.ROLE_BANK.getRole(), RolesEnums.ROLE_ADMIN.getRole(), RolesEnums.ROLE_OWNER.getRole())
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AuthenticationTokenFilter(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**", "/h2-console/**");

    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
