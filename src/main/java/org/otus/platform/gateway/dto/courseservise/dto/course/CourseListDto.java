package org.otus.platform.gateway.dto.courseservise.dto.course;

import java.util.List;

public record CourseListDto(
        List<CourseDto> courses
) {
}
