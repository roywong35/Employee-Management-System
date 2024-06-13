package net.javaguides.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import net.javaguides.springboot.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private UserService userService;

    @Bean
    static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(requests -> requests.requestMatchers("/registration**").permitAll().anyRequest()
                .authenticated()).formLogin(login -> login.loginPage("/login").permitAll()).logout(logout -> logout
                .invalidateHttpSession(true).clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
                .permitAll());

		return http.build();

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}

}