package com.jamanchi.summary;

import com.jamanchi.summary.dto.SummaryCountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SummarySevice {
    private final SummaryRepository summaryRepository;
    public SummaryCountDto getUseCount() {
        Summary useCounter = summaryRepository.findById(1L).orElseThrow(() -> new RuntimeException("데이터가 없습니다"));
        return new SummaryCountDto(useCounter.getCategory(), useCounter.getValue(), useCounter.getModifiedAt());
    }

    public Summary incrementUseCount() {
        Summary useCounter = summaryRepository.findById(1L).orElseThrow(() -> new RuntimeException("데이터가 없습니다"));
        useCounter.increseValue();
        return useCounter;
    }
}
