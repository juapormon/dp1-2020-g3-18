package org.springframework.samples.petclinic.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author japarejo
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/resources/**","/webjars/**","/h2-console/**").permitAll()
				.antMatchers(HttpMethod.GET, "/","/oups").permitAll()
				.antMatchers("/users/new").permitAll()
				.antMatchers("/admin/**").hasAnyAuthority("admin")
				.antMatchers("/scores/**").permitAll()
				.antMatchers("/deans/**").hasAnyAuthority("dean","admin")
				.antMatchers("/colleges/**").hasAnyAuthority("dean","admin") 
				.antMatchers("/teachers").permitAll()
				.antMatchers("/teachers/new").hasAnyAuthority("dean","admin")
				.antMatchers("/myTeachers/**").hasAnyAuthority("student","admin")
				.antMatchers("/teachers/*").permitAll()
				.antMatchers("/teachers/*/scores").permitAll()
				.antMatchers("/teachers/*/scores/new").hasAnyAuthority("admin", "student")
				.antMatchers("/studentsWithScore").hasAnyAuthority("admin")
				.antMatchers("/students/**").permitAll()
				.antMatchers("/students/new").permitAll()
				.antMatchers("/subjects/mySubjects/{studentId}").hasAnyAuthority("student")
				.antMatchers("/teachers/*/scores/*/edit").hasAnyAuthority("admin")
				.antMatchers("/subjects/**").permitAll()
				.antMatchers("/teachersWithScore").hasAnyAuthority("admin")
				.antMatchers("/findTeachers").permitAll()
				.antMatchers("/teachersFound").permitAll()
				.antMatchers("/departments/**").permitAll()
				.anyRequest().denyAll()
				.and()
				 	.formLogin()
				 	/*.loginPage("/login")*/
				 	.failureUrl("/login-error")
				.and()
					.logout()
						.logoutSuccessUrl("/"); 
                // Configuración para que funcione la consola de administración 
                // de la BD H2 (deshabilitar las cabeceras de protección contra
                // ataques de tipo csrf y habilitar los framesets si su contenido
                // se sirve desde esta misma página.
                http.csrf().ignoringAntMatchers("/h2-console/**");
                http.headers().frameOptions().sameOrigin();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
	      .dataSource(dataSource)
	      .usersByUsernameQuery(
	       "select username,password,enabled "
	        + "from users "
	        + "where username = ?")
	      .authoritiesByUsernameQuery(
	       "select username, authority "
	        + "from authorities "
	        + "where username = ?")	      	      
	      .passwordEncoder(passwordEncoder());	
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {	    
		PasswordEncoder encoder =  NoOpPasswordEncoder.getInstance();
	    return encoder;
	}
	
}


