package com.jamanchi.commons.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PageResponseDto<T> {

    private List<T> contents;
    private boolean isLast;

    public PageResponseDto(List<T> contents, boolean isLast) {
        this.contents = contents;
        this.isLast = isLast;
    }
}
