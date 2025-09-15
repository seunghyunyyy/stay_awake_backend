package com.swake.app.dto.session;


import jakarta.validation.constraints.NotBlank;


public record StartSessionRequest(
        @NotBlank String deviceLabel
) {}