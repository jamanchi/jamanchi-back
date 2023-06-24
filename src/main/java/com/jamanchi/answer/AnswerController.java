package com.jamanchi.answer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "답변")
@RestController
@RequestMapping("/api/v1/answer")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @GetMapping("")
    @Operation(summary = "답변 결과 조회", description = "취미정보를 토대로 gpt에게 답변을 받아옵니다.")
    public void getAnswer() {
        answerService.getAnswer();
    }
}
