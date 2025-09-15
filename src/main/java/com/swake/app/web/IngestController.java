package com.swake.app.web;


import com.swake.app.common.ApiKeyResolver;
import com.swake.app.common.ApiResponse;
import com.swake.app.domain.session.Session;
import com.swake.app.dto.ingest.DrowsinessEventDTO;
import com.swake.app.dto.ingest.HeartRateDTO;
import com.swake.app.service.IngestService;
import com.swake.app.service.SessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/ingest")
@RequiredArgsConstructor
public class IngestController {
    private final ApiKeyResolver resolver;
    private final SessionService sessions;
    private final IngestService ingest;


    public record HeartRateResult(Long id, int bpm) {}
    public record EventResult(Long id, int score) {}


    private Session requireActiveSession(String apiKey) {
        var user = resolver.resolve(apiKey);
        var s = sessions.current(user);
        if (s == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No active session");
        return s;
    }


    @PostMapping("/heart-rate")
    public ApiResponse<HeartRateResult> pushHeartRate(@RequestHeader("X-API-KEY") String apiKey,
                                                      @Valid @RequestBody HeartRateDTO req) {
        var s = requireActiveSession(apiKey);
        var hr = ingest.addHeartRate(s, req.bpm(), req.source());
        return ApiResponse.ok(new HeartRateResult(hr.getId(), hr.getBpm()));
    }


    @PostMapping("/drowsiness")
    public ApiResponse<EventResult> pushEvent(@RequestHeader("X-API-KEY") String apiKey,
                                              @Valid @RequestBody DrowsinessEventDTO req) {
        var s = requireActiveSession(apiKey);
        var ev = ingest.addEvent(s, req.score(), req.source(), req.severity(), req.note());
        return ApiResponse.ok(new EventResult(ev.getId(), ev.getScore()));
    }
}