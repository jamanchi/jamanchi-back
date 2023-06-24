package com.jamanchi.hobby.dto;

import com.jamanchi.hobby.Hobby;
import com.jamanchi.keyword.Keyword;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class HobbyRequestDto {

    @Getter
    @NoArgsConstructor
    public static class Create{
        @NotBlank(message = "이름은 필수 입력 값입니다.")
        private String name;

        private String parentName;

        public Create(String name, String parentName) {
            this.name = name;
            this.parentName = parentName;
        }
    }
}
