package br.com.alura.mvc.mudi;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
				.antMatchers("/", "/home").permitAll()
				.anyRequest()
				.authenticated()
			.and()
				.formLogin((form) -> 
				form.loginPage("/login")
					.defaultSuccessUrl("/usuario/home", true)
					.permitAll())
			.logout(logout -> 
				logout.logoutUrl("/logout")
				.logoutSuccessUrl("/home"))
			.csrf().disable();

		return http.build();
	}

	@Bean
	public DataSource dataSource() {
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.url("jdbc:mysql://localhost:3306/AB_TREINAMENTO_JACKSOM");
		dataSourceBuilder.username("jack");
		dataSourceBuilder.password("password");
		return dataSourceBuilder.build();
	}

	@Bean
	public UserDetailsService users(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}