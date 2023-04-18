package augusto108.ces.studenttracker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:users.properties")
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private @Value("${users.username.user1}") String user1;
    private @Value("${users.username.user2}") String user2;
    private @Value("${users.roles.role1}") String role1;
    private @Value("${users.roles.role2}") String role2;
    private @Value("${users.password}") String password;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        User.UserBuilder users = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(users.username(user1).password(password).roles(role1))
                .withUser(users.username(user2).password(password).roles(role1, role2));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/**").hasRole(role1)
                .antMatchers(HttpMethod.PUT, "/**").hasRole(role1)
                .antMatchers(HttpMethod.POST, "/**").hasRole(role2)
                .antMatchers(HttpMethod.DELETE, "/**").hasRole(role2)
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
