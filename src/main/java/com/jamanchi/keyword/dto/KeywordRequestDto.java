package com.jamanchi.keyword.dto;

import com.jamanchi.keyword.Keyword;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class KeywordRequestDto {

    @Getter
    @NoArgsConstructor
    public static class Create{
        @NotBlank(message = "이름은 필수 입력 값입니다.")
        private String name;

        public Create(String name) {
            this.name = name;
        }

        public Keyword toEntity(){
            return new Keyword(name);
        }
    }
}
