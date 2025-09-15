package com.swake.app.web;


import com.swake.app.common.ApiResponse;
import com.swake.app.domain.user.User;
import com.swake.app.dto.auth.SignInRequest;
import com.swake.app.dto.auth.SignUpRequest;
import com.swake.app.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService auth;


    public record AuthResult(Long id, String email, String apiKey) {}


    @PostMapping("/signup")
    public ApiResponse<AuthResult> signUp(@Valid @RequestBody SignUpRequest req) {
        User u = auth.signUp(req.email(), req.password());
        return ApiResponse.ok(new AuthResult(u.getId(), u.getEmail(), u.getApiKey()));
    }


    @PostMapping("/signin")
    public ApiResponse<AuthResult> signIn(@Valid @RequestBody SignInRequest req) {
        User u = auth.signIn(req.email(), req.password());
        return ApiResponse.ok(new AuthResult(u.getId(), u.getEmail(), u.getApiKey()));
    }
}