package org.otus.platform.gateway.controller.courseservice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.otus.platform.gateway.client.courseservice.VebinarClient;
import org.otus.platform.gateway.dto.courseservise.dto.vebinar.VebinarCreateRequest;
import org.otus.platform.gateway.dto.courseservise.dto.vebinar.VebinarDto;
import org.otus.platform.gateway.dto.courseservise.dto.vebinar.VebinarListDto;
import org.otus.platform.gateway.dto.courseservise.dto.vebinar.VebinarUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/course/vebinar")
@Validated
@RequiredArgsConstructor
@Tag(
        name = "Vebinar controller",
        description = "Vebinar API"
)
public class VebinarController {

    private final VebinarClient vebinarCLient;

    @GetMapping("/list/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT', 'ROLE_TEACHER')")
    @Operation(summary = "Get all vebinars for the course by courseId")
    ResponseEntity<VebinarListDto> getVebinarListByCourse(@NotNull @PathVariable UUID id) {
        return vebinarCLient.getCourseScheduleListByCourse(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT', 'ROLE_TEACHER')")
    @Operation(summary = "Get vebinar by vebinarId")
    ResponseEntity<VebinarDto> getVebinarById(@NotNull @PathVariable UUID id) {
        return vebinarCLient.getCourseScheduleById(id);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Create vebinar")
    ResponseEntity<VebinarDto> createVebinar(@Valid @RequestBody VebinarCreateRequest request) {
        return vebinarCLient.createCourseSchedule(request);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Update vebinar")
    ResponseEntity<VebinarDto> updateVebinar(@Valid @RequestBody VebinarUpdateRequest request) {
        return vebinarCLient.updateCourseSchedule(request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Delete vebinar")
    ResponseEntity<Void> deleteVebinarById(@NotNull @PathVariable UUID id) {
        return vebinarCLient.deleteScheduleById(id);
    }
}
