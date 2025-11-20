package com.egov.service; import com.egov.dto.*; public interface AuthService { ApiResponse register(RegisterRequest request); AuthResponse login(LoginRequest request); }
