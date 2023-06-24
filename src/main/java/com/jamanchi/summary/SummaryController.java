package com.jamanchi.summary;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("")
    @Operation(summary = "서비스 이용 횟수 조회", description = "현재 서비스 이용 횟수를 조회합니다.")
    public void getUseCount() {
        summaryService.getUseCount();
    }

    @PutMapping("")
    @Operation(summary = "서비스 이용 횟수 수정", description = "현재 서비스 이용 횟수를 1개 올립니다.")
    public void incrementUseCount() {
        summaryService.incrementUseCount();
    }
}
