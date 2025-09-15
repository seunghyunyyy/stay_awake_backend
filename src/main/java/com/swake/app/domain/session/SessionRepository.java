package com.swake.app.domain.session;


import com.swake.app.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findFirstByUserAndActiveIsTrueOrderByStartedAtDesc(User user);
}