package com.prolificidea.templates.tsw.web.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.security.SignatureException;
import java.util.*;

/**
 * Created by matthew.jordaan on 2016/03/09.
 */
@Service
public class TokenAuthenticationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenAuthenticationService.class);

    public static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

    public static final String CLAIM_USERNAME = "username";
    public static final String CLAIM_AUTHORITIES = "authorities";

    //TODO: Need to store this in some properties file
    final Key secretKey = MacProvider.generateKey();

    public String getToken(CustomUser user) {

        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_USERNAME, user.getUsername());
        claims.put(CLAIM_AUTHORITIES, user.getAuthorities());

        String jwtToken = Jwts.builder().setIssuer("http://misana.com").setClaims(claims).signWith(SignatureAlgorithm.HS512, secretKey).compact();

        return jwtToken;
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null) {
            final CustomUser user = getUserFromToken(token);
            LOGGER.info("User: {} unpacked from token: {}", user, token);
            if (user != null) {
                return new UserAuthentication(user);
            }
        }
        return null;
    }

    private CustomUser getUserFromToken(String token) {
        try {

            final Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();

            List<GrantedAuthority> actualAuthorities = getActualAuthorities(claims.get(CLAIM_AUTHORITIES));

            final CustomUser customUser = new CustomUser((String) claims.get(CLAIM_USERNAME), actualAuthorities);
            return customUser;
            // TODO: Verify user in DB????
        } catch (SignatureException e) {
            LOGGER.info(e.getMessage(), e);
            return null;
        }
    }

    /**
     * For some reason jjwt unpacks the token with an authorities claim that is List<LinkedHashMap> instead of List<SimpleGrantedAuthority>
     * This method is a workaround for that
     */
    private List<GrantedAuthority> getActualAuthorities(Object claim) {
        List<GrantedAuthority> actualAuthorities = new ArrayList<>();


        List<LinkedHashMap> authorities = (ArrayList<LinkedHashMap>) claim;
        String authority;
        for (LinkedHashMap map : authorities) {
            authority = (String) map.get("authority");
            actualAuthorities.add(new SimpleGrantedAuthority(authority));
        }

        return actualAuthorities;
    }
}
