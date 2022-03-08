//package com.pjt.flowing.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests()
//// 어떤 요청이든 '인증' //
////                .anyRequest().authenticated() // 로그인 개발후에 인증,인가 가능하게 해야함
//                .anyRequest().permitAll()
//                .and()
//// 로그인 기능 허용
//                .formLogin()
//                .defaultSuccessUrl("/")
//                .permitAll()
//                .and()
//// 로그아웃 기능 허용
//                .logout()
//                .permitAll();
//    }
//}