package com.redsam.adfsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@EnableWebSecurity(debug=false)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    

    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
         http
            .authorizeRequests()
                    .antMatchers("/","/faces/login.jspx", "/faces/public.jspx","/login.html", "/adflogin", "/adf/", "/afr/" ).permitAll()
                    .anyRequest().authenticated()
                    .and()
            .formLogin()
                    .loginPage("/login.html")
                .defaultSuccessUrl("/faces/home.jspx",true)
                    .failureUrl("/faces/login.jspx?error=true")
                    .and()
            .logout()
                    .permitAll();
        
        http.csrf().disable(); 
    }
            
         
  
        @Bean
        @Override
        public UserDetailsService userDetailsService() {
            UserDetails user =
                User.withDefaultPasswordEncoder().username("redsam").password("redsam").roles("WHATEVER").build();
    
            return new InMemoryUserDetailsManager(user);
        }
}
