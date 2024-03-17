package org.otus.platform.gateway.dto.courseservise.dto.course;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record JoinToCourseRequest(
        @NotNull
        UUID userId,
        @NotNull
        UUID courseId,
        @NotNull
        UserType userType
) {
}
