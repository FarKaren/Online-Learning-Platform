package org.otus.platform.gateway.dto.courseservise.dto.schedule;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import org.otus.platform.gateway.dto.courseservise.dto.course.LessonStatus;

import java.time.ZonedDateTime;
import java.util.UUID;

public record CourseScheduleCreateRequest(
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
