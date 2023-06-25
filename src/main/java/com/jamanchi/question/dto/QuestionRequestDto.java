package com.jamanchi.question.dto;

import com.jamanchi.question.Question;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class QuestionRequestDto {

    @Getter
    @NoArgsConstructor
    public static class Create{
        @NotBlank(message = "질문은 필수 입력 값입니다.")
        private String question;

        public Create(String question) {
            this.question = question;
        }

        public Question toEntity(){
            return new Question(question);
        }
    }
}
