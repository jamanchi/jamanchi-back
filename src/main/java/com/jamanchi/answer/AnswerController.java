package com.jamanchi.answer;

import com.jamanchi.answer.dto.AnswerResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "답변")
@RestController
@RequestMapping("/api/v1/answer")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @GetMapping("")
    @Operation(summary = "답변 결과 조회", description = "취미정보를 토대로 db 데이터에 있는 답변을 받아옵니다. (가격, 필요물품, 소개 기본제공으로 추가 키워드id만 전달필요)")
    public ResponseEntity<AnswerResponseDto.DbResponse> getAnswerDB(@RequestParam Integer hobbyId, @RequestParam Integer keywordId1, @RequestParam Integer keywordId2) {
        return ResponseEntity.ok(answerService.getAnswerDB(hobbyId, keywordId1, keywordId2));
    }

    @GetMapping("/gpt")
    @Operation(summary = "답변 결과 조회", description = "취미정보를 토대로 db에 데이터가 없으면 gpt에게 답변을 받아옵니다.")
    public ResponseEntity<AnswerResponseDto.GptResponse> getAnswerGPT(@RequestParam Integer hobbyId, @RequestParam Integer keywordId) {
        return ResponseEntity.ok(answerService.getAnswerGPT(hobbyId, keywordId));
    }
}
