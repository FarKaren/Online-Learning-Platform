package org.otus.platform.gateway.dto.courseservise.dto.course;


import org.otus.platform.gateway.dto.userservice.UserDto;

import java.util.List;

public record CourseUserListDto(
        List<UserDto> users
) {
}
