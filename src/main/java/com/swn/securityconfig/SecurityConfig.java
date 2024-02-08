package com.swn.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.swn.service.UserService;





@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	
	/*@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((authorize) -> authorize
				.anyRequest().authenticated()
			)
			.httpBasic(Customizer.withDefaults())
			.formLogin(Customizer.withDefaults());

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails userDetails = User.withDefaultPasswordEncoder()
			.username("admin")
			.password("softshop")
			.roles("USER")
			.build();

		return new InMemoryUserDetailsManager(userDetails);
	}
   */
	
	@Autowired
	private UserService userService;



	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

 /*
	@Bean
	public SecurityFilterChain securityFilterChainRegistration(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(
			auth -> auth.requestMatchers("/registration**").permitAll()
			    .anyRequest())
                .formLogin(login -> login
                        .loginPage("/registration")
                        .permitAll())
                .logout(logout -> logout
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout")
                        .permitAll());

		return http.build();
	}

*/
	/*
	@Bean
	public SecurityFilterChain securityFilterChainLoggedIn(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(
			auth -> auth.requestMatchers("/js/**","/css/**","/img/**").permitAll()
			    .anyRequest()
			    .authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .permitAll())
                .logout(logout -> logout
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout")
                        .permitAll());

		return http.build();
	}
*/
	
	@Bean
	public SecurityFilterChain securityFilterChainLoggedIn(HttpSecurity http) throws Exception {
	    
	    		return http
	    		        .authorizeHttpRequests(authorizeRequests ->
	    		            authorizeRequests
	    		                .requestMatchers(
	    		                    "/registration**",
	    		                    "/js/**",
	    		                    "/css/**",
	    		                    "/img/**").permitAll()
	    		                .anyRequest().authenticated()
	    		        )
	    		        .formLogin(formLogin -> formLogin
	    		         .loginPage("/login")
	    		            .permitAll()
	    		        )
	    		        .logout(logout -> logout
	    		            .invalidateHttpSession(true)
	    		            .clearAuthentication(true)
	    		            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	    		        .logoutSuccessUrl("/login?logout")
	    		            //.logoutSuccessUrl("/custom-logout")
	    		            .permitAll()
	    		        )
	    		        .build();
	}
	/*
	@Bean
	public SecurityFilterChain securityFilterChainLoggedIn(HttpSecurity http) throws Exception {

		http.authorizeRequests().requestMatchers(
				 "/registration**",
	                "/js/**",
	                "/css/**",
	                "/img/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
		.logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout")
		.permitAll();

		return http.build();
	}
	*/

	
	@Bean
	public AuthenticationManager authenticationManager(
			UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);

		return new ProviderManager(authenticationProvider);
	}


	

}