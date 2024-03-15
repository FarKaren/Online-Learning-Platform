package org.otus.platform.gateway.controller.courseservice;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.otus.platform.gateway.client.courseservice.CourseScheduleClient;
import org.otus.platform.gateway.dto.courseservise.dto.schedule.CourseScheduleCreateRequest;
import org.otus.platform.gateway.dto.courseservise.dto.schedule.CourseScheduleDto;
import org.otus.platform.gateway.dto.courseservise.dto.schedule.CourseScheduleListDto;
import org.otus.platform.gateway.dto.courseservise.dto.schedule.CourseScheduleUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/course/schedule")
@Validated
@RequiredArgsConstructor
public class CourseScheduleController {

    private final CourseScheduleClient courseScheduleCLient;

    @GetMapping("/list/{courseId}")
    ResponseEntity<CourseScheduleListDto> getCourseScheduleListByCourse(@NotNull @PathVariable("courseId") UUID id) {
        return courseScheduleCLient.getCourseScheduleListByCourse(id);
    }

    @GetMapping("/{id}")
    ResponseEntity<CourseScheduleDto> getCourseScheduleById(@NotNull @PathVariable UUID id) {
        return courseScheduleCLient.getCourseScheduleById(id);
    }

    @PostMapping("/create")
    ResponseEntity<CourseScheduleDto> createCourseSchedule(@Valid @RequestBody CourseScheduleCreateRequest request) {
        return courseScheduleCLient.createCourseSchedule(request);
    }

    @PutMapping("/update")
    ResponseEntity<CourseScheduleDto> updateCourseSchedule(@Valid @RequestBody CourseScheduleUpdateRequest request) {
        return courseScheduleCLient.updateCourseSchedule(request);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteScheduleById(@NotNull @PathVariable UUID id) {
        return courseScheduleCLient.deleteScheduleById(id);
    }
}
