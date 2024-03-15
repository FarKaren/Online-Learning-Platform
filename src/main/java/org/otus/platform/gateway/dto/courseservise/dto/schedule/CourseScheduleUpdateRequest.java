package org.otus.platform.gateway.dto.courseservise.dto.schedule;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.otus.platform.gateway.dto.courseservise.dto.course.LessonStatus;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
public record CourseScheduleUpdateRequest(
        @NotNull
        UUID id,
        @NotNull
        UUID courseId,
        @NotNull
        String courseTitle,
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ")
        ZonedDateTime lessonDate,
        @NotNull
        LessonStatus lessonStatus
) {
}
