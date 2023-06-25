package com.jamanchi.hobby.dto;

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

    @Getter
    @NoArgsConstructor
    public static class UpdateImage{
        @NotBlank(message = "이름은 필수 입력 값입니다.")
        private String name;

        public UpdateImage(String name) {
            this.name = name;
        }
    }
}
