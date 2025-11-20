package com.egov.controller; 
import com.egov.dto.*;
import com.egov.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/auth") @RequiredArgsConstructor public class AuthController {
    private final AuthService authService;
    @PostMapping("/register") public ResponseEntity<ApiResponse> register(@RequestBody RegisterRequest r){ return ResponseEntity.ok(authService.register(r)); }
    @PostMapping("/login") public ResponseEntity<?> login(@RequestBody LoginRequest r){
        try{ 
            return ResponseEntity.ok(authService.login(r));
        } catch(Exception e){
            return ResponseEntity.status(401).body(new ApiResponse(false,e.getMessage(),null));
        } 
    }
}
