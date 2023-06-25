package com.jamanchi.question;

import com.jamanchi.question.dto.QuestionRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="질문", description = "질문에 관한 API입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/questions")
public class QuestionController {

    private final QuestionService questionService;

    @Operation(summary = "질문 생성", description = "새로운 질문을 생성합니다.")
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody QuestionRequestDto.Create requestDto){
        questionService.create(requestDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Operation(summary = "모든 질문 조회", description = "모든 질문을 조회합니다.")
    @GetMapping
    public ResponseEntity<List<String>> getAll(){
        return ResponseEntity.ok(questionService.getAll());
    }

    @Operation(summary = "모든 질문 삭제", description = "모든 질문을 삭제합니다.")
    @DeleteMapping
    public ResponseEntity<Void> deleteAll(){
        questionService.deleteAll();
        return new ResponseEntity(HttpStatus.OK);
    }
}
