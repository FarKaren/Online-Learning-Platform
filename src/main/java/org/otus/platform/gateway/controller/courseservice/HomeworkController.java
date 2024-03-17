package org.otus.platform.gateway.controller.courseservice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.otus.platform.gateway.client.courseservice.HomeworkClient;
import org.otus.platform.gateway.dto.courseservise.dto.homework.HomeworkCreateRequest;
import org.otus.platform.gateway.dto.courseservise.dto.homework.HomeworkDto;
import org.otus.platform.gateway.dto.courseservise.dto.homework.HomeworkUpdateRequest;
import org.otus.platform.gateway.dto.courseservise.dto.homework.UpdateHomeworkStatusRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/course/homework")
@Validated
@RequiredArgsConstructor
@Tag(
        name = "Homework controller",
        description = "Homework API"
)
public class HomeworkController {

    private final HomeworkClient homeworkClient;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT', 'ROLE_TEACHER')")
    @Operation(summary = "Get homework by id")
    ResponseEntity<HomeworkDto> getHomeworkById(@NotNull @PathVariable UUID id) {
        return homeworkClient.getHomeworkById(id);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
    @Operation(summary = "Create homework")
    ResponseEntity<HomeworkDto> createHomework(@Valid @RequestBody HomeworkCreateRequest request) {
        return homeworkClient.createHomework(request);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')")
    @Operation(summary = "Update homework")
    ResponseEntity<HomeworkDto> updateHomework(@Valid @RequestBody HomeworkUpdateRequest request) {
        return homeworkClient.updateHomework(request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Delete homework")
    ResponseEntity<Void> deleteHomework(@NotNull @PathVariable UUID id) {
        return homeworkClient.deleteHomework(id);
    }

    @PutMapping("/update/homework/status")
    @PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_TEACHER')")
    @Operation(summary = "Update homework status")
    ResponseEntity<HomeworkDto> updateHomeworkStatus(@Valid @RequestBody UpdateHomeworkStatusRequest request) {
        return homeworkClient.updateHomeworkStatus(request);
    }
}
