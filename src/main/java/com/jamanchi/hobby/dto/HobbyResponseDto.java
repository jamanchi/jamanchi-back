package com.jamanchi.hobby.dto;

import lombok.Getter;

import java.util.List;

public class HobbyResponseDto {

    @Getter
    public static class Info {
        private Integer id;
        private String name;

        public Info(Integer id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    @Getter
    public static class All {
        private Integer id;
        private String name;
        private String image;

        public All(Integer id, String name, String image) {
            this.id = id;
            this.name = name;
            this.image = image;
        }

        public void setImageUrl(String url){
            image = url + image;
        }
    }
}
