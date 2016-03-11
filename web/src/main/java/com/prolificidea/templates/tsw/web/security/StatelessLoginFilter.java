package com.prolificidea.templates.tsw.web.security;

import com.prolificidea.templates.tsw.services.security.CustomUser;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by matthew.jordaan on 2016/03/10.
 */
public class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter {

    private final UserDetailsService userDetailsService;
    private final TokenAuthenticationService tokenAuthenticationService;

    public StatelessLoginFilter(String urlMapping, UserDetailsService userDetailsService, TokenAuthenticationService tokenAuthenticationService, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(urlMapping));
        setAuthenticationManager(authenticationManager);
        this.userDetailsService = userDetailsService;
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        System.out.println(request.getInputStream());
        final CustomUser user = new ObjectMapper().readValue(request.getInputStream(), CustomUser.class);
        final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword());
        return getAuthenticationManager().authenticate(loginToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authentication) throws IOException, ServletException {

        // Lookup the complete User object from the database and create an Authentication for it
        final CustomUser user = (CustomUser) userDetailsService.loadUserByUsername(authentication.getName());

        // Add the custom token as HTTP header to the response
        response.addHeader(TokenAuthenticationService.AUTH_HEADER_NAME, tokenAuthenticationService.getToken(user));
        // Add the authentication to the Security context
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
