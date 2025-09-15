package com.swake.app.domain.session;


import com.swake.app.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.Instant;


@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "sessions")
public class Session {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;


    @CreationTimestamp
    private Instant startedAt;


    private Instant endedAt;


    @Column(nullable = false)
    private boolean active;
}