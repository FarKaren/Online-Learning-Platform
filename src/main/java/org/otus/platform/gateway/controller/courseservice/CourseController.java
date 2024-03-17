package org.otus.platform.gateway.controller.courseservice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.otus.platform.gateway.client.courseservice.CourseClient;
import org.otus.platform.gateway.dto.courseservise.dto.course.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/course")
@Validated
@RequiredArgsConstructor
@Tag(
        name = "Course controller",
        description = "Course API"
)
public class CourseController {

    private final CourseClient courseClient;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT', 'ROLE_TEACHER')")
    @Operation(summary = "Get course by id")
    ResponseEntity<CourseDto> getCourseById(@NotNull @PathVariable UUID id) {
        return courseClient.getCourseById(id);
    }

    @GetMapping("/list/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT', 'ROLE_TEACHER')")
    @Operation(summary = "Get course list by user id")
    ResponseEntity<CourseListDto> getCoursesByUser(@NotNull @PathVariable UUID id) {
        return courseClient.getCoursesByUser(id);
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
    @Operation(summary = "Get user list by course id")
    ResponseEntity<CourseUserListDto> getUsersByCourse(@NotNull @PathVariable UUID id) {
        return courseClient.getUsersByCourse(id);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Create course")
    ResponseEntity<CourseDto> createCourse(@Valid @RequestBody CreateCourseRequest request) {
        return courseClient.createCourse(request);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Update course")
    ResponseEntity<CourseDto> updateCourse(@Valid @RequestBody UpdateCourseRequest request) {
        return courseClient.updateCourse(request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Operation(summary = "Delete course")
    ResponseEntity<Void> deleteCourse(@NotNull @PathVariable UUID id) {
        return courseClient.deleteCourse(id);
    }


    @PostMapping("/join")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Operation(summary = "Join user to course")
    ResponseEntity<CourseDto> joinToCourse(@Valid @RequestBody JoinToCourseRequest request) {
        return courseClient.joinToCourse(request);
    }
}
