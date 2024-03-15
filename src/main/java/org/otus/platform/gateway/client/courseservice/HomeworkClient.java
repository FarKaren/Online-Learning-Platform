package org.otus.platform.gateway.client.courseservice;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.otus.platform.gateway.dto.courseservise.dto.homework.HomeworkCreateRequest;
import org.otus.platform.gateway.dto.courseservise.dto.homework.HomeworkDto;
import org.otus.platform.gateway.dto.courseservise.dto.homework.HomeworkUpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient("COURSESERVICE/course/homework")
public interface HomeworkClient {

    @GetMapping("/{id}")
    ResponseEntity<HomeworkDto> getHomeworkById(@NotNull @PathVariable UUID id);

    @PostMapping("/create")
    ResponseEntity<HomeworkDto> createHomework(@Valid @RequestBody HomeworkCreateRequest request);

    @PutMapping("/update")
    ResponseEntity<HomeworkDto> updateHomework(@Valid @RequestBody HomeworkUpdateRequest request);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteHomework(@NotNull @PathVariable UUID id);
}
