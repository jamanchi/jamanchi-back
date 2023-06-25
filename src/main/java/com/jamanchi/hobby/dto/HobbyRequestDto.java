package com.jamanchi.hobby.dto;

import com.jamanchi.hobby.Hobby;
import com.jamanchi.keyword.Keyword;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

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

        private MultipartFile image;

        public UpdateImage(String name, MultipartFile image) {
            this.name = name;
            this.image = image;
        }
    }
}
