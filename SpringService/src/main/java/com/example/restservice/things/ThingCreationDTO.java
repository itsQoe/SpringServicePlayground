package com.example.restservice.things;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ThingCreationDTO(
        @Size(min = 3, max = 256)
        @NotNull
        String name,
        @Size(max = 1024)
        String description
) {
}
