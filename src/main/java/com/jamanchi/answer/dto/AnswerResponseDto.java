package com.jamanchi.answer.dto;

import lombok.Getter;

@Getter
public class AnswerResponseDto {
    private Integer id;
    private Integer hobbyId;
    private Integer kewyordId;
    private String gptAnswer;

    public AnswerResponseDto(Integer id, Integer hobbyId, Integer kewyordId, String gptAnswer) {
        this.id = id;
        this.hobbyId = hobbyId;
        this.kewyordId = kewyordId;
        this.gptAnswer = gptAnswer;
    }
}
