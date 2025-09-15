package com.swake.app.dto.ingest;


import com.swake.app.domain.event.SourceType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


public record HeartRateDTO(
        @NotNull SourceType source,
        @Min(1) int bpm
) {}