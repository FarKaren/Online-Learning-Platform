package org.otus.platform.gateway.client.courseservice;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.otus.platform.gateway.dto.courseservise.dto.course.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient("COURSESERVICE/course")
public interface CourseClient {

    @GetMapping("/{id}")
    ResponseEntity<CourseDto> getCourseById(@NotNull @PathVariable UUID id);
    @GetMapping("/list/{id}")
    ResponseEntity<CourseListDto> getCoursesByUser(@NotNull @PathVariable UUID id);

    @GetMapping("/users/{id}")
    ResponseEntity<CourseUserListDto> getUsersByCourse(@NotNull @PathVariable UUID id);

    @PostMapping("/create")
    ResponseEntity<CourseDto> createCourse(@Valid @RequestBody CreateCourseRequest request);
    @PutMapping("/update")
    ResponseEntity<CourseDto> updateCourse(@Valid @RequestBody UpdateCourseRequest request);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCourse(@NotNull @PathVariable UUID id);

    @PostMapping("/join")
    ResponseEntity<CourseDto> joinToCourse(@Valid @RequestBody JoinToCourseRequest request);

}
