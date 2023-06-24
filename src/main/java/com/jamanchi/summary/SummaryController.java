package com.jamanchi.summary;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "통계정보")
@RestController
@RequestMapping("/api/v1/summary")
@RequiredArgsConstructor
public class SummaryController {
    private final SummarySevice summaryService;

    @GetMapping("/count")
    @Transactional(readOnly = true)
    @Operation(summary = "서비스 이용 횟수 조회", description = "현재 서비스 이용 횟수를 조회합니다.")
    public ResponseEntity getUseCount() {
        return new ResponseEntity(summaryService.getUseCount(), HttpStatus.OK);
    }

    @PutMapping("/count")
    @Transactional
    @Operation(summary = "서비스 이용 횟수 수정", description = "현재 서비스 이용 횟수를 1개 올립니다.")
    public ResponseEntity incrementUseCount() {
        return new ResponseEntity(summaryService.incrementUseCount(), HttpStatus.OK);
    }
}
