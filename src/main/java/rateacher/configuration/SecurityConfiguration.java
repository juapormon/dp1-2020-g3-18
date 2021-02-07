
package rateacher.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
				.antMatchers("/teachers/**").permitAll()
				.antMatchers("/teachers/*").hasAnyAuthority("admin", "student", "dean", "teacher")
				.antMatchers("/teachers/*/scores").permitAll()
				.antMatchers("/teachers/*/scores/new").hasAnyAuthority("admin", "student")
				.antMatchers("/teachers/{teacherId}/studentsRated").hasAnyAuthority("admin")
				.antMatchers("/teachers/{teacherId}/newPersonalExperience").hasAnyAuthority("teacher")
				.antMatchers("/teachers/{subjectId}/subjectsTeached").permitAll()
				.antMatchers("/teachers/*/scores/*/edit").hasAnyAuthority("admin")
				.antMatchers("/teachersWithScore").hasAnyAuthority("admin")
				.antMatchers("/findTeachers").permitAll()
				.antMatchers("/teachersFound").permitAll()
				.antMatchers("/teachersByDepartment").permitAll()
				.antMatchers("/teachersByDepartment/**").permitAll()
				.antMatchers("/myTeachers").permitAll()
				.antMatchers("/studentsWithScore").hasAnyAuthority("admin")
				.antMatchers("/students/**").permitAll()
				.antMatchers("/students/new").permitAll()
				.antMatchers("/subjects/mySubjects/*").permitAll()
				.antMatchers("/subjects/**").permitAll()
				.antMatchers("/subjects/*/teachers").permitAll()
				.antMatchers("/departments/**").permitAll()
				.antMatchers("/reports").hasAnyAuthority("teacher")
				.antMatchers("/reports/**").hasAnyAuthority("teacher")
				.antMatchers("/teachers/{teacherId}/studentsRated").hasAnyAuthority("admin")
				.antMatchers("/teachers/{teacherId}/newPersonalExperience").permitAll()
				.antMatchers("/teachers/{subjectId}/subjectsTeached").permitAll()
				.antMatchers("/subjects/{subjectId}/newTeachingPlan").hasAnyAuthority("teacher")
				.antMatchers("/personalExperience/{personalExperienceId}").permitAll()
				.antMatchers("/personalExperience/{personalExperienceId}/newResearchExperience").hasAnyAuthority("teacher")
				.antMatchers("/personalExperience/{personalExperienceId}/newTeachingExperience").hasAnyAuthority("teacher")
				.antMatchers("/personalExperience/{personalExperienceId}/newProfessionalExperience").hasAnyAuthority("teacher")
				.antMatchers("/personalExperience/{personalExperienceId}/newExternalEvaluation").hasAnyAuthority("teacher")
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
				http.exceptionHandling().accessDeniedPage("/forbiddenAccess");
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


