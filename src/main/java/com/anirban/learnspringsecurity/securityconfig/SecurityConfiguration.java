package com.anirban.learnspringsecurity.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //Since this is a security config class we use this annotation
@EnableWebSecurity //This annotation enables custom security configuration
@EnableMethodSecurity 
/*
By applying the @EnableMethodSecurity annotation to your Spring configuration class, 
you enable method-level security for your application. 
This allows you to annotate methods with security annotations 
to specify who can access those methods and under what Roles.
*/
public class SecurityConfiguration {


/*The UserDetailsService interface is a core component of Spring Security, and it plays a critical role in the authentication process. 
It's designed to retrieve user information and build a UserDetails object, which is used for authentication and authorization.*/
	
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder) { //This bean is for Authentication purpose
////Using UserDetails objects we are initializing user-names roles and password
////In real project we will fetch user details from DB and the authenticate 		
//		UserDetails admin = User.withUsername("anirban.das")
//				                 .password(encoder.encode("adminpassword"))
//				                 .roles("ADMIN")
//				                 .build();
///*In this context, the encoder method is responsible for securely hashing the passwords using the configured password encoder, 
// * which is a BCryptPasswordEncoder in your case.*/
//		
//		UserDetails normalUser = User.withUsername("tushar.kumar") //User is a built in class that has a model of user in it
//                .password(encoder.encode("normaluserpassword"))    //We use User class to store username and passowrd and much more
//                .roles("USER") 
//                .build();
//		
//	return new InMemoryUserDetailsManager(admin,normalUser);//Since we dont have a database to verify the creds we are using this
////The InMemoryUserDetailsManager is a class provided by Spring Security that allows you to define and manage user details in memory
////Used mainly for dev and testing purposes 
		
		return new UserInfoUserDetailsService();
	}

	//Here is the password encoder configuration Bean
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//Using authorization we can define which role is eligible for hitting which end-point 	
	@Bean
	public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception{//This bean is for Authorization purpose
		return http.csrf().disable()
		           .authorizeHttpRequests().requestMatchers("/products/welcome").permitAll()
		           .and().authorizeHttpRequests().requestMatchers("/products/addNewUser").permitAll()
		           .and().authorizeHttpRequests()
		                  .requestMatchers("/products/**").authenticated()
		                  .and().formLogin()
		                  .and().build();
		
		
	}
	
	/*
"http.csrf().disable()" This line disables CSRF (Cross-Site Request Forgery) protection for your application. 
CSRF is a security feature that helps prevent malicious websites from making unauthorized requests on behalf of a user. 
In some cases, you might choose to disable CSRF protection

".authorizeHttpRequests()" This part indicates that you're going to define rules for request authorization.

".requestMatchers("/products/welcome").permitAll()" Here, you specify that requests to the path /products/welcome are permitted for all users, 
regardless of whether they are authenticated or not

".requestMatchers("/products/**").authenticated()" This part specifies that requests to paths under /products/ are only allowed for 
authenticated (logged-in) users. 
Any URL matching /products/ and its sub-paths will require users to log in to access those resources.

".formLogin()" This indicates that you want to enable form-based authentication i.e Username and Password

".and().build()" These lines conclude the configuration setup.
	*/

}
