package com.swake.app.service;


import com.swake.app.domain.session.Session;
import com.swake.app.domain.session.SessionRepository;
import com.swake.app.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.Instant;


@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessions;


    @Transactional
    public Session start(User user) {
        var current = sessions.findFirstByUserAndActiveIsTrueOrderByStartedAtDesc(user);
        if (current.isPresent()) return current.get();
        var s = Session.builder().user(user).active(true).build();
        return sessions.save(s);
    }


    @Transactional
    public Session end(User user) {
        var s = sessions.findFirstByUserAndActiveIsTrueOrderByStartedAtDesc(user)
                .orElseThrow(() -> new IllegalStateException("No active session"));
        s.setActive(false);
        s.setEndedAt(Instant.now());
        return s;
    }


    @Transactional(readOnly = true)
    public Session current(User user) {
        return sessions.findFirstByUserAndActiveIsTrueOrderByStartedAtDesc(user).orElse(null);
    }
}