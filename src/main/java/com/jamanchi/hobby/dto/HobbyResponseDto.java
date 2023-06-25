package com.jamanchi.hobby.dto;

import lombok.Getter;

import java.util.List;

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
    }

    @Getter
    public static class Main {
        private Info main;
        private List<Info> subList;

        public Main(Info main, List<Info> subList) {
            this.main = main;
            this.subList = subList;
        }
    }
}
