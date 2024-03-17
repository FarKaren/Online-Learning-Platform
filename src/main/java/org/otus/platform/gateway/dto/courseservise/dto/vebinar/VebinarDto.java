package org.otus.platform.gateway.dto.courseservise.dto.vebinar;

import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
public record VebinarDto(
        UUID id,
        UUID courseId,
        UUID teacherId,
        String title,
        ZonedDateTime lessonDate,
        String summary,
        String task
) {
}
