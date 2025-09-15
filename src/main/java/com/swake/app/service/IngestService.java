package com.swake.app.service;


import com.swake.app.domain.event.*;
import com.swake.app.domain.hr.*;
import com.swake.app.domain.session.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class IngestService {
    private final HeartRateRepository heartRates;
    private final DrowsinessEventRepository events;


    @Transactional
    public HeartRateSample addHeartRate(Session session, int bpm, SourceType source) {
        var hr = HeartRateSample.builder()
                .session(session)
                .bpm(bpm)
                .source(source)
                .build();
        return heartRates.save(hr);
    }


    @Transactional
    public DrowsinessEvent addEvent(Session session, int score, SourceType source, DrowsinessSeverity severity, String note) {
        var ev = DrowsinessEvent.builder()
                .session(session)
                .score(score)
                .source(source)
                .severity(severity)
                .note(note)
                .build();
        return events.save(ev);
    }
}