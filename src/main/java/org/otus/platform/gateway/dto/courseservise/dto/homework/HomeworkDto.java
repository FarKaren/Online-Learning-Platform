package org.otus.platform.gateway.dto.courseservise.dto.homework;

import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
public record HomeworkDto(
        UUID id,
        UUID course,
        UUID student,
        UUID teacher,
        Boolean onReview,
        ZonedDateTime added
) {
}
