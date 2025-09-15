package com.swake.app.web;


import com.swake.app.common.ApiResponse;
import com.swake.app.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/stats")
@RequiredArgsConstructor
public class StatsController {
    private final StatsService stats;


    public record Summary(long heartRates, long events) {}


    @GetMapping("/summary")
    public ApiResponse<Summary> summary() {
        return ApiResponse.ok(new Summary(stats.countAllHeartRates(), stats.countAllEvents()));
    }
}