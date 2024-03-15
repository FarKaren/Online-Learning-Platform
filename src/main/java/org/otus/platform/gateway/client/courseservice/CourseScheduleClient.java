package org.otus.platform.gateway.client.courseservice;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.otus.platform.gateway.dto.courseservise.dto.schedule.CourseScheduleCreateRequest;
import org.otus.platform.gateway.dto.courseservise.dto.schedule.CourseScheduleDto;
import org.otus.platform.gateway.dto.courseservise.dto.schedule.CourseScheduleListDto;
import org.otus.platform.gateway.dto.courseservise.dto.schedule.CourseScheduleUpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient("COURSESERVICE/course/schedule")
public interface CourseScheduleClient {

    @GetMapping("/list/{courseId}")
    ResponseEntity<CourseScheduleListDto> getCourseScheduleListByCourse(@NotNull @PathVariable("courseId") UUID id);

    @GetMapping("/{id}")
    ResponseEntity<CourseScheduleDto> getCourseScheduleById(@NotNull @PathVariable UUID id);

    @PostMapping("/create")
    ResponseEntity<CourseScheduleDto> createCourseSchedule(@Valid @RequestBody CourseScheduleCreateRequest request);

    @PutMapping("/update")
    ResponseEntity<CourseScheduleDto> updateCourseSchedule(@Valid @RequestBody CourseScheduleUpdateRequest request);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteScheduleById(@NotNull @PathVariable UUID id);
}
