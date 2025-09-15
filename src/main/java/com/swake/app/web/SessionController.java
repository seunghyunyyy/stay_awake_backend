package com.swake.app.web;


import com.swake.app.common.ApiKeyResolver;
import com.swake.app.common.ApiResponse;
import com.swake.app.domain.session.Session;
import com.swake.app.dto.session.StartSessionRequest;
import com.swake.app.service.SessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class SessionController {
    private final ApiKeyResolver resolver;
    private final SessionService sessions;


    public record SessionResult(Long id, boolean active) {}


    @PostMapping("/start")
    public ApiResponse<SessionResult> start(@RequestHeader("X-API-KEY") String apiKey,
                                            @Valid @RequestBody StartSessionRequest req) {
        var user = resolver.resolve(apiKey);
        Session s = sessions.start(user);
        return ApiResponse.ok(new SessionResult(s.getId(), s.isActive()));
    }


    @PostMapping("/end")
    public ApiResponse<SessionResult> end(@RequestHeader("X-API-KEY") String apiKey,
                                          @RequestBody(required = false) Object ignore) {
        var user = resolver.resolve(apiKey);
        Session s = sessions.end(user);
        return ApiResponse.ok(new SessionResult(s.getId(), s.isActive()));
    }
}