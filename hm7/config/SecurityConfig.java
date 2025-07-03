package com.ho.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//@RequiredArgsConstructor
public class SecurityConfig {

    		
	
	//private final UserDetailsServiceImpl userDetailsService;
	
	// 1. Configure HTTP security rules
    @Bean		
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
    	 //======================No browser login screen + Test everything via Postman or frontend with Authorization header
		//==(you try to access a protected API without logging in means local testing/developer only)=================  
    	
    	
		http
		.csrf(csrf -> csrf.disable())			// Disable CSRF for testing (Postman / REST)
		.authorizeHttpRequests
							(auth -> auth.requestMatchers(
															"/api/admins/**",
															"/api/employees/**",
															"/api/rooms/**",
															"/api/patients/**",
															"/api/doctors/**",
															"/api/appointments/**",
															"/api/prescriptions/**",
															"/api/invoices/**",
															"/api/medicalrecords/**",
															"/api/nurses/**"
								
														).authenticated()	
		
									//.anyRequest().permitAll() 			//can access everything without any authentication.(NO security)
							)
		.httpBasic(Customizer.withDefaults())  			// Use only Basic Auth (API style)
		.formLogin(AbstractHttpConfigurer::disable);  // Disable HTML login page
		
		return http.build();
		}
    
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
    
    
    
    // 2️⃣ In-memory user for testing purpose only (admin / admin123)
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("admin123")
            .roles("ADMIN")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
}






    	//=================you try to access a protected API with logging in.=====================================
//        http
//            	.csrf(csrf -> csrf.disable())  // Disable CSRF for testing (you can enable later)
//            	.authorizeHttpRequests
//            	(auth -> auth
//            		.requestMatchers("/login", "/error").permitAll()
//            		.requestMatchers(HttpMethod.POST, "/api/patients").authenticated() // POST needs login
//            		 .requestMatchers(HttpMethod.GET, "/api/patients/**").authenticated()
//                     .anyRequest().authenticated()
//            	)
//            .formLogin(form -> form.permitAll());  //(allow basic Auth for postman)default login page provided by Spring Security
            		
//            .logout(logout -> logout.permitAll());
    	
  
    

