package org.otus.platform.gateway.controller.courseservice;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.otus.platform.gateway.client.courseservice.CourseClient;
import org.otus.platform.gateway.dto.courseservise.dto.course.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/course")
@Validated
@RequiredArgsConstructor
public class CourseController {

    private final CourseClient courseClient;

    @GetMapping("/{id}")
    ResponseEntity<CourseDto> getCourseById(@NotNull @PathVariable UUID id) {
        return courseClient.getCourseById(id);
    }

    @GetMapping("/list/{userId}")
    ResponseEntity<CourseListDto> getCoursesByUser(@NotNull @PathVariable UUID id) {
        return courseClient.getCoursesByUser(id);
    }

    @GetMapping("/users/{courseId}")
    ResponseEntity<CourseUserListDto> getUsersByCourse(@NotNull @PathVariable UUID id) {
        return courseClient.getUsersByCourse(id);
    }

    @PostMapping("/create")
    ResponseEntity<CourseDto> createCourse(@Valid @RequestBody CreateCourseRequest request) {
        return courseClient.createCourse(request);
    }

    @PutMapping("/update")
    ResponseEntity<CourseDto> updateCourse(@Valid @RequestBody UpdateCourseRequest request) {
        return courseClient.updateCourse(request);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCourse(@NotNull @PathVariable UUID id) {
        return courseClient.deleteCourse(id);
    }
}
