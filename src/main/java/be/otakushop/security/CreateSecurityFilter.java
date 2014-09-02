package be.otakushop.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebMvcSecurity
public class CreateSecurityFilter extends WebSecurityConfigurerAdapter {
	private static final String USERS_BY_USERNAME = "SELECT emailadres AS username, wachtwoord AS password, actief AS enabled FROM gebruikers WHERE emailadres = ?";
	private static final String AUTHORITIES_BY_USERNAME = "SELECT gebruikers.emailadres AS username, rollen.naam AS authorities FROM gebruikers INNER JOIN gebruikersrollen ON gebruikers.id = gebruikersrollen.gebruikerid INNER JOIN rollen ON rollen.id = gebruikersrollen.rolid WHERE gebruikers.emailadres = ?";
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(USERS_BY_USERNAME).authoritiesByUsernameQuery(AUTHORITIES_BY_USERNAME).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/images/**").antMatchers("/styles/**").antMatchers("/scripts/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().defaultSuccessUrl("/gebruiker").loginPage("/login").and().logout().logoutSuccessUrl("/")
		.and().authorizeRequests().antMatchers("/bestellingen/**").authenticated()
		.and().authorizeRequests().antMatchers(HttpMethod.GET, "/gebruiker").authenticated()
		.and().exceptionHandling().accessDeniedPage("/WEB-INF/JSP/forbidden.jsp");
	}

}
