package org.otus.platform.gateway.dto.courseservise.dto.course;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record CreateCourseRequest(
        @NotNull
        String title,
        @NotNull
        BigDecimal price,
        @NotNull
        ZonedDateTime startAt,
        @NotNull
        ZonedDateTime endAt
) {
}
