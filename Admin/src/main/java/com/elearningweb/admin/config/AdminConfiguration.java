package com.elearningweb.admin.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class AdminConfiguration {

    @Bean
    public UserDetailsService userDetailsService() {
        return new AdminServiceConfig();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeHttpRequests().requestMatchers("/*").permitAll()
//                .requestMatchers("/admin/*")
//                .hasAuthority("ADMIN")
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/do-login")
//                .defaultSuccessUrl("/index")
////                .failureForwardUrl("/login?error")
//                .permitAll()
//                .and()
//                .logout()
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login?logout")
//                .permitAll();

        http
                .csrf().disable()
                .authorizeHttpRequests((authorize) ->
                                authorize
//                                .requestMatchers("/register").permitAll()
//                                .requestMatchers("/register/**").permitAll()
//                                .requestMatchers("/index/**").permitAll()
                                        .requestMatchers("/user").hasAuthority("USER")
                                        .requestMatchers("/user/**").hasAuthority("USER")
//                                .requestMatchers("/admin").permitAll()
//                                .requestMatchers("/admin/**").permitAll()
                                        .anyRequest().permitAll()
                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/do-login")
                                .failureForwardUrl("/login?error")
                                .defaultSuccessUrl("/index")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .logoutSuccessUrl("/login?logout")
                                .permitAll()
                ).httpBasic();

        return http.build();
    }


//    protected void  configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }

//    protected void configure(HttpSecurity http) throws Exception{
//        http.authorizeHttpRequests().requestMatchers("/*").permitAll()
//                .requestMatchers("/admin/*")
//                .hasAuthority("ADMIN")
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/do-login")
//                .defaultSuccessUrl("/admin/index")
//                .permitAll()
//                .and()
//                .logout()
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login?logout")
//                .permitAll();
//
//    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() throws Exception {
        return (web) -> web.ignoring()
                .requestMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico");
    }
}
