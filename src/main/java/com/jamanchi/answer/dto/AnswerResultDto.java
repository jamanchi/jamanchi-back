package com.jamanchi.answer.dto;

import lombok.Getter;

@Getter
public class AnswerResultDto {
    private String keyword;
    private String description;

    public AnswerResultDto(String keyword, String description) {
        this.keyword = keyword;
        this.description = description;
    }
}
