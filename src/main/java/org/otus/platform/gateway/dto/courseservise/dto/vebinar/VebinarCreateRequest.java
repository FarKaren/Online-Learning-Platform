package org.otus.platform.gateway.dto.courseservise.dto.vebinar;

import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;
import java.util.UUID;

public record VebinarCreateRequest(
        @NotNull
        UUID courseId,
        @NotNull
        @NotNull
        UUID teacherId,
        String title,
        @NotNull
        ZonedDateTime lessonDate,
        @NotNull
        String summary,
        @NotNull
        String task
) {
}
