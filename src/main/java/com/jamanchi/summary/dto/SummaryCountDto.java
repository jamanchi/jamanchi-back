package com.jamanchi.summary.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SummaryCountDto {
    private String name;
    private Integer value;
    private LocalDateTime last_updated;

    public SummaryCountDto(String name, Integer value, LocalDateTime last_updated) {
        this.name = name;
        this.value = value;
        this.last_updated = last_updated;
    }
}
