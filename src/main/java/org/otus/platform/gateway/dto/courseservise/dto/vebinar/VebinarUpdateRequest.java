package org.otus.platform.gateway.dto.courseservise.dto.vebinar;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
public record VebinarUpdateRequest(
        @NotNull
        UUID id,
        @NotNull
        UUID courseId,
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
