package org.otus.platform.gateway.dto.courseservise.dto.vebinar;

import java.util.List;

public record MonthVebinarListDto(
        Integer monthNumber,
        List<VebinarDto> vebinars
) {
}
