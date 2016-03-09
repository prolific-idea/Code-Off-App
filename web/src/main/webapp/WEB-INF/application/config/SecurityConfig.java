import com.prolificidea.templates.tsw.services.providers.AppUserDetailsService;
import com.prolificidea.templates.tsw.services.providers.AppUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by matthew.jordaan on 2016/03/09.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    AppUserDetailsService appUserDetailsService;

    @Autowired
    TokenAuthenticationService tokenAuthenticationService;

    @Autowired
    AuthenticationManager authenticationManager;


}
