package com.swake.app.dto.ingest;


import com.swake.app.domain.event.DrowsinessSeverity;
import com.swake.app.domain.event.SourceType;
import jakarta.validation.constraints.*;


public record DrowsinessEventDTO(
        @NotNull SourceType source,
        @NotNull DrowsinessSeverity severity,
        @Min(0) @Max(100) int score,
        String note
) {}