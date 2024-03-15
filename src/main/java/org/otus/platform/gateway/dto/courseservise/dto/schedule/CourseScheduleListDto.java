package org.otus.platform.gateway.dto.courseservise.dto.schedule;

import java.util.List;

public record CourseScheduleListDto(
        List<CourseScheduleDto> schedules
) {
}
