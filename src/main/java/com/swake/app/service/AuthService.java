package com.swake.app.service;


import com.swake.app.domain.user.User;
import com.swake.app.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository users;


    @Transactional
    public User signUp(String email, String password) {
        users.findByEmail(email).ifPresent(u -> { throw new IllegalArgumentException("Email already used"); });
        var hash = sha256(password);
        var apiKey = UUID.randomUUID().toString().replace("-", "");
        var user = User.builder()
                .email(email)
                .passwordHash(hash)
                .apiKey(apiKey)
                .build();
        return users.save(user);
    }


    @Transactional(readOnly = true)
    public User signIn(String email, String password) {
        var hash = sha256(password);
        var user = users.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
        if (!user.getPasswordHash().equals(hash)) throw new IllegalArgumentException("Invalid credentials");
        return user; // apiKey는 생성 시 고정. 필요 시 재발급 로직 추가 가능
    }


    private static String sha256(String s) {
        try {
            var md = MessageDigest.getInstance("SHA-256");
            var bytes = md.digest(s.getBytes(StandardCharsets.UTF_8));
            var sb = new StringBuilder();
            for (byte b : bytes) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) { throw new RuntimeException(e); }
    }
}