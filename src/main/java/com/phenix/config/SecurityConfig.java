package  com.phenix.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // require all requests to be authenticated except for the resources
        http.authorizeRequests()
                .antMatchers("/javax.faces.resource/**")
                .permitAll()
                .antMatchers("/h2-console/**")
                .permitAll()
                .anyRequest()
                .authenticated();
        // login
        http.formLogin()
                .loginPage("/login.xhtml")
                .permitAll()
                .failureUrl("/login.xhtml?error=true");
        // logout
        http.logout()
                .logoutSuccessUrl("/login.xhtml");

        // not needed as JSF 2.2 is implicitly protected against CSRF
        http.csrf().disable();

        http.headers().frameOptions().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("ddrad")
                .password("{noop}1234")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("{noop}1234")
                .roles("ADMIN");
    }
}
