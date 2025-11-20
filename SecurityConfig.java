package com.egov.config; import com.egov.security.JwtFilter; import com.egov.service.impl.AuthServiceImpl; import lombok.RequiredArgsConstructor; import org.springframework.context.annotation.*; import org.springframework.security.authentication.*; import org.springframework.security.config.annotation.web.builders.HttpSecurity; import org.springframework.security.config.http.SessionCreationPolicy; import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import org.springframework.security.web.*; import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration @RequiredArgsConstructor public class SecurityConfig {
    private final JwtFilter jwtFilter; private final AuthServiceImpl authService;
    @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests().requestMatchers("/api/auth/**","/v3/api-docs/**","/swagger-ui/**","/h2-console/**").permitAll().anyRequest().authenticated().and().userDetailsService(authService).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); http.headers().frameOptions().disable(); return http.build();
    }
    @Bean public BCryptPasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder(); }
}
