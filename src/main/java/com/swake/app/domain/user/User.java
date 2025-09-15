package com.swake.app.domain.user;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.Instant;


@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true, length = 120)
    private String email;


    @Column(nullable = false)
    private String passwordHash; // 데모: Plain hash text (실서비스: BCrypt 권장)


    @Column(nullable = false, unique = true, length = 64)
    private String apiKey;


    @CreationTimestamp
    private Instant createdAt;
}