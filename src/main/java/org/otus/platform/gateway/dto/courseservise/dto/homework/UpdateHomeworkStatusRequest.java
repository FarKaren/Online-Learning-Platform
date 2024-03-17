package org.otus.platform.gateway.dto.courseservise.dto.homework;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateHomeworkStatusRequest(
        @NotNull
        UUID homeworkId,
        @NotNull
        CompleteStatus completeStatus
) {
}
