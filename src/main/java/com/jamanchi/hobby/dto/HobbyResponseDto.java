package com.jamanchi.hobby.dto;

import lombok.Getter;

public class HobbyResponseDto {

    @Getter
    public static class Info {
        private Integer id;
        private String name;
        private String image;

        public Info(Integer id, String name, String image) {
            this.id = id;
            this.name = name;
            this.image = image;
        }

        public void setImageUrl(String url){
            image = url + image;
        }
    }
}
