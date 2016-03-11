package com.prolificidea.templates.tsw.web.security.config;

import com.prolificidea.templates.tsw.services.providers.AppUserDetailsService;
import com.prolificidea.templates.tsw.web.security.StatelessAuthenticationFilter;
import com.prolificidea.templates.tsw.web.security.StatelessLoginFilter;
import com.prolificidea.templates.tsw.web.security.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

/**
 * Created by matthew.jordaan on 2016/03/09.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    TokenAuthenticationService tokenAuthenticationService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth)throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

         http.authorizeRequests()
                //allow anonymous resource requests
                .antMatchers("/","/index.html").permitAll()
                .antMatchers("/api/appUserDetails/grant/**").permitAll()
                .antMatchers("/api/leaderboard/**").permitAll()
//                //allow anonymous POSTs to register and login
                .antMatchers("api/user/login").permitAll()
                .antMatchers(HttpMethod.POST, "/api/user/register").permitAll()
                .antMatchers(HttpMethod.POST, "/api/user/login").permitAll()
                .antMatchers("api/challenges/**").hasRole("SIMPLE")
////                //all other request need to be authenticated
                .anyRequest().hasRole("SIMPLE").and()
//
                .addFilterBefore(new StatelessLoginFilter("/api/user/login", userDetailsService, tokenAuthenticationService, authenticationManager), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Configures the web security(Adds to the Spring Security Filter Chain) to ignore the matched URLS.
     * Used to configure global exclusions. Role or user based security should be handled by the {@code configure(HttpSecurity)}
     * This is for all the static resources. Css, Js, Images etc.
     *
     * @param web The {@code WebSecurity} object that will be used to build up the Security Filter.
     * @throws Exception If any of the builder methods throws an {@code Exception}
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setSessionAttributeName("_csrf");
        return repository;
    }
}
