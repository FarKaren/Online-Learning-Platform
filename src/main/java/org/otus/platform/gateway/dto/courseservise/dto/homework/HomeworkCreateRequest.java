package org.otus.platform.gateway.dto.courseservise.dto.homework;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;
@Builder
public record HomeworkCreateRequest(
        @NotNull
        UUID courseId,
        @NotNull
        UUID studentId,
        @NotNull
        UUID teacherId,
        @NotNull
        String content
) {
}
