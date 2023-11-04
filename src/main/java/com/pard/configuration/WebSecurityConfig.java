//package com.pard.configuration;
//
//import com.pard.filter.JwtAuthenticationFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig {
//
//    private JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    @Autowired
//    public WebSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
//        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//    }
//
//    @Bean
//    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                //cors정책 (현재는 Application에서 작업을 해뒀으므로 기본 설정 사용)
//                .cors(Customizer.withDefaults())
//                //csrf 대책 (현재는 CSRF에 대한 대책을 비활성화)
//                .csrf((csrf) -> csrf.disable())
//                //basic 인증 (현재는 bearer token 인증방법을 사용하기 때문에 비활성화)
//                .httpBasic((httpBasic) -> httpBasic.disable())
//                // 세션 기반 인증 (현재는 Session 기반 인증을 사용하지 않기 때문에 상태를 없앰)
//                .sessionManagement((sessionManagement) ->sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                // "/" "/api/auth" 모듈에 대해서는 모두 허용 (인증을 하지 않고 사용 가능하게 함)
//                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests.requestMatchers("/*","/pard/*")
//                        //나머지 REquest에 대해서는 모두 인증된 사용자만 사용가능하게함
//                        .permitAll().anyRequest().authenticated());
//
//        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return httpSecurity.build();
//    }
//
//    @Bean
//    public PasswordEncoder getPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}