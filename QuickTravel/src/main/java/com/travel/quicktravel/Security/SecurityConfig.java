package com.travel.quicktravel.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userdetailsservice;
        
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
        
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//		.withUser("admin").password(new BCryptPasswordEncoder().encode("123")).roles("ADMIN","USER")
//		.and()
//		.withUser("user").password(new BCryptPasswordEncoder().encode("123")).roles("USER");
		auth.userDetailsService(userdetailsservice)
		.passwordEncoder(bCryptPasswordEncoder);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		//http.formLogin();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/login/**").permitAll();
                http.authorizeRequests().antMatchers("/quicktravel/**").permitAll();
                http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
//                http.authorizeRequests().antMatchers(HttpMethod.GET, "/secutel/user/all").authenticated();
//                http.authorizeRequests().antMatchers(HttpMethod.GET, "/ihs/exportportfolio/excel").hasAuthority("PM")
//                .and()
//                .authorizeRequests().antMatchers(HttpMethod.POST,"/ihs/portfolio/import").hasAuthority("PM");
//		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
		http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	
}
