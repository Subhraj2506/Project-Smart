package com.egov.service.impl;
import com.egov.dto.*; import com.egov.model.*; import com.egov.repository.UserRepository; import com.egov.security.JwtUtil;
import com.egov.service.AuthService; import lombok.RequiredArgsConstructor; import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*; import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import org.springframework.stereotype.Service;
import java.util.Collections;
@Service @RequiredArgsConstructor
public class AuthServiceImpl implements AuthService, UserDetailsService {
    private final UserRepository userRepository; private final JwtUtil jwtUtil; private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override public ApiResponse register(RegisterRequest request){
        if(userRepository.findByEmail(request.getEmail()).isPresent()) return new ApiResponse(false,"Email exists",null);
        User u = User.builder().fullName(request.getFullName()).email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).role(Role.ROLE_CITIZEN).build();
        userRepository.save(u); return new ApiResponse(true,"Registered",null);
    }
    @Override public AuthResponse login(LoginRequest request){
        User u = userRepository.findByEmail(request.getEmail()).orElseThrow(()->new RuntimeException("User not found"));
        if(!passwordEncoder.matches(request.getPassword(), u.getPassword())) throw new RuntimeException("Invalid credentials");
        String token = jwtUtil.generateToken(u.getEmail());
        return new AuthResponse(token,"Bearer", u);
    }
    @Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User u = userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(u.getEmail(), u.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(u.getRole().name())));
    }
}
