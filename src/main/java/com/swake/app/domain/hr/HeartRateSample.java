package com.swake.app.domain.hr;


import com.swake.app.domain.session.Session;
import com.swake.app.domain.event.SourceType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.Instant;


@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "heart_rate_samples")
public class HeartRateSample {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Session session;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SourceType source; // WATCH/PHONE/etc.


    @Column(nullable = false)
    private int bpm;


    @CreationTimestamp
    private Instant createdAt;
}