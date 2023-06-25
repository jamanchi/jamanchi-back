package com.jamanchi.answer.dto;

import lombok.Getter;

@Getter
public class AnswerResultDto {
    private String kewyord;
    private String dsecription;

    public AnswerResultDto(String kewyord, String dsecription) {
        this.kewyord = kewyord;
        this.dsecription = dsecription;
    }
}
