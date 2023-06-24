package com.jamanchi.keyword.dto;

import lombok.Builder;
import lombok.Getter;

public class KeywordResponseDto {

    @Getter
    @Builder
    public static class Info {
        private Integer id;
        private String name;

        public Info(Integer id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
