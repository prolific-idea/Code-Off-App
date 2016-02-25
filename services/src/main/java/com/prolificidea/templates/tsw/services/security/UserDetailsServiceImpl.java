package com.prolificidea.templates.tsw.services.security;

import java.util.ArrayList;
import java.util.List;

import com.prolificidea.templates.tsw.domain.AppUser;
import com.prolificidea.templates.tsw.domain.AppUserRole;
import com.prolificidea.templates.tsw.persistence.AppUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.naming.NamingException;


@Service ("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private AppUserDao appUserDao;
	
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser = appUserDao.getAppUserByUsername(username);
        if( appUser != null) {
            try {
                return buildSpringUserFromAppUser(appUser);
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        return null;
	}
	
	public UserDetails buildSpringUserFromAppUser(AppUser appUser) throws NamingException {
        String username = appUser.getUsername();
		String password = appUser.getPassword();

		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        for(AppUserRole appUserRole: appUser.getAppUserRoles()) {
            authorities.add(new SimpleGrantedAuthority(appUserRole.getRole().getDescription()));
        }

        UserDetails currentUser = new User(username, password, authorities);

		return currentUser;
	}

}
