package org.otus.platform.gateway.client.courseservice;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.otus.platform.gateway.dto.courseservise.dto.vebinar.VebinarCreateRequest;
import org.otus.platform.gateway.dto.courseservise.dto.vebinar.VebinarDto;
import org.otus.platform.gateway.dto.courseservise.dto.vebinar.VebinarListDto;
import org.otus.platform.gateway.dto.courseservise.dto.vebinar.VebinarUpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient("COURSESERVICE/course/vebinar")
public interface VebinarClient {

    @GetMapping("/list/{id}")
    ResponseEntity<VebinarListDto> getCourseScheduleListByCourse(@NotNull @PathVariable UUID id);

    @GetMapping("/{id}")
    ResponseEntity<VebinarDto> getCourseScheduleById(@NotNull @PathVariable UUID id);

    @PostMapping("/create")
    ResponseEntity<VebinarDto> createCourseSchedule(@Valid @RequestBody VebinarCreateRequest request);

    @PutMapping("/update")
    ResponseEntity<VebinarDto> updateCourseSchedule(@Valid @RequestBody VebinarUpdateRequest request);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteScheduleById(@NotNull @PathVariable UUID id);
}
