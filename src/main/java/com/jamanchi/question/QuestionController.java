package com.jamanchi.question;

import com.jamanchi.question.dto.QuestionRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="질문", description = "질문에 관한 API입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/questions")
public class QuestionController {

    private final QuestionService questionService;

    @Operation(summary = "Create Question", description = "질문 생성")
    @PostMapping
    public void create(@RequestBody QuestionRequestDto.Create requestDto){
        questionService.create(requestDto);
    }

    @Operation(summary = "Get Questions", description = "모든 질문 조회")
    @GetMapping
    public ResponseEntity<List<String>> getAll(){
        return ResponseEntity.ok(questionService.getAll());
    }

    @Operation(summary = "Delete All", description = "모든 질문 데이터 삭제")
    @DeleteMapping
    public void deleteAll(){
        questionService.deleteAll();
    }
}
