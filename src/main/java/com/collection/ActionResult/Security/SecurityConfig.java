package com.collection.ActionResult.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.collection.ActionResult.ServiceImpl.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtFilter jwtFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http.csrf(Customizer -> Customizer.disable())// disable coz we want http statless
				.authorizeHttpRequests(request -> request.requestMatchers("/user/register").permitAll()
						.requestMatchers("/user/login").permitAll().anyRequest().authenticated())
//		.formLogin(Customizer.withDefaults())//for browser login form 
//		.httpBasic(Customizer.withDefaults())//for postman rest api access
				.sessionManagement(Session -> Session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(this.jwtFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

//	 @Bean
//	 public UserDetailsService userDetailsService()
//	 {
//		 UserDetails user1=User.withDefaultPasswordEncoder()
//				 .username("santy")
//				 .password("santy@123")
//				 .roles("ADMIN")
//				 .build();
//		 return new InMemoryUserDetailsManager(user1);
//	 }

//	public AuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setPasswordEncoder(new BCryptPasswordEncoder());
//		provider.setUserDetailsService(userDetailsService);
//		return provider;
//	}

	/**
	 * @param authenticationManagerBuilder
	 * @throws Exception
	 */
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}

	/**
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}
