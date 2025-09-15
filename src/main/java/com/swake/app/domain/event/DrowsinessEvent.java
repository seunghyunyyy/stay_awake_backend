package com.swake.app.domain.event;


import com.swake.app.domain.session.Session;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.Instant;


@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "drowsiness_events")
public class DrowsinessEvent {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Session session;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SourceType source;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DrowsinessSeverity severity;


    @Column(nullable = false)
    private int score; // 0~100 등급 등


    private String note;


    @CreationTimestamp
    private Instant createdAt;
}