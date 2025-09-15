package com.swake.app.common;


import com.swake.app.domain.user.User;
import com.swake.app.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;


@Component
@RequiredArgsConstructor
public class ApiKeyResolver {
    private final UserRepository users;


    public User resolve(String apiKey) {
        if (apiKey == null || apiKey.isBlank()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "X-API-KEY header is required");
        }
        return users.findByApiKey(apiKey).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid API key")
        );
    }
}