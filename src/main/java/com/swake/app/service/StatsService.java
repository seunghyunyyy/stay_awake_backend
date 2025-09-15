package com.swake.app.service;


import com.swake.app.domain.hr.HeartRateRepository;
import com.swake.app.domain.event.DrowsinessEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class StatsService {
    private final HeartRateRepository heartRates;
    private final DrowsinessEventRepository events;


    @Transactional(readOnly = true)
    public long countAllHeartRates() { return heartRates.count(); }


    @Transactional(readOnly = true)
    public long countAllEvents() { return events.count(); }
}