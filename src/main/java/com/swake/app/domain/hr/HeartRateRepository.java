package com.swake.app.domain.hr;


import org.springframework.data.jpa.repository.JpaRepository;


public interface HeartRateRepository extends JpaRepository<HeartRateSample, Long> {}