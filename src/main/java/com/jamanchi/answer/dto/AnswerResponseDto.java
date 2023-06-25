package com.jamanchi.answer.dto;

import lombok.Getter;

import java.util.List;

public class AnswerResponseDto {
    @Getter
    public static class DbResponse {
        private String title;
        private String image;
        private List<AnswerResultDto> result;

        public DbResponse(String title, String image, List<AnswerResultDto> result) {
            this.title = title;
            this.image = image;
            this.result = result;
        }
    }

    @Getter
    public static class GptResponse {
        private Integer id;
        private Integer hobbyId;
        private Integer kewyordId;
        private String gptAnswer;

        public GptResponse(Integer id, Integer hobbyId, Integer kewyordId, String gptAnswer) {
            this.id = id;
            this.hobbyId = hobbyId;
            this.kewyordId = kewyordId;
            this.gptAnswer = gptAnswer;
        }
    }
}
