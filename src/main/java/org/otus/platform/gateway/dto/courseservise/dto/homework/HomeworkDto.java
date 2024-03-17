package org.otus.platform.gateway.dto.courseservise.dto.homework;

import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
public record HomeworkDto(
        UUID id,
        UUID courseId,
        UUID studentId,
        UUID teacherId,
        UUID vebinarId,
        CompleteStatus completeStatus,
        ZonedDateTime added,
        String content
) {
}
