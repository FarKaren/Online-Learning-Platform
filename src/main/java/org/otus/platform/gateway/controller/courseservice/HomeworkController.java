package org.otus.platform.gateway.controller.courseservice;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.otus.platform.gateway.client.courseservice.HomeworkClient;
import org.otus.platform.gateway.dto.courseservise.dto.homework.HomeworkCreateRequest;
import org.otus.platform.gateway.dto.courseservise.dto.homework.HomeworkDto;
import org.otus.platform.gateway.dto.courseservise.dto.homework.HomeworkUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/course/homework")
@Validated
@RequiredArgsConstructor
public class HomeworkController {

    private final HomeworkClient homeworkClient;

    @GetMapping("/{id}")
    ResponseEntity<HomeworkDto> getHomeworkById(@NotNull @PathVariable UUID id) {
        return homeworkClient.getHomeworkById(id);
    }

    @PostMapping("/create")
    ResponseEntity<HomeworkDto> createHomework(@Valid @RequestBody HomeworkCreateRequest request) {
        return homeworkClient.createHomework(request);
    }

    @PutMapping("/update")
    ResponseEntity<HomeworkDto> updateHomework(@Valid @RequestBody HomeworkUpdateRequest request) {
        return homeworkClient.updateHomework(request);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteHomework(@NotNull @PathVariable UUID id) {
        return homeworkClient.deleteHomework(id);
    }
}
