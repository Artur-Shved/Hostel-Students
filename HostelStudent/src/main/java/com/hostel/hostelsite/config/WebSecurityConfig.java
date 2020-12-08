package com.hostel.hostelsite.config;

import com.hostel.hostelsite.service.CustomAuthenticationProvider;
import com.hostel.hostelsite.service.SecondUserService;
import com.hostel.hostelsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    UserService service;

    @Autowired
    SecondUserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/addnewuser").hasRole("SUPER_ADMIN")
                .antMatchers("/delete/**").hasRole("SUPER_ADMIN")
                .antMatchers("/editemp/**").hasAnyRole("ADMIN", "SUPER_ADMIN")
                .antMatchers("/login","/registration", "/css/login-page.css", "/css/registration-style.css")
                .permitAll()

                .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll();
    }





//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(service)
//                .passwordEncoder(NoOpPasswordEncoder.getInstance());
//    }

//
//    @Autowired
//    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(service).passwordEncoder(bCryptPasswordEncoder());
//    }
}
