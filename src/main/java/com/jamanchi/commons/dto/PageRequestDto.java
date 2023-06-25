package com.jamanchi.commons.dto;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;

public class PageRequestDto {
    private int page;
    private int size;

    public void setPage(int page) {
        this.page = page <= 0 ? 1 : page;
    }

    public void setSize(int size) {
        int DEFAULT_SIZE = 10;
        int MAX_SIZE = 50;
        this.size = size > MAX_SIZE ? DEFAULT_SIZE : size;
    }

    public PageRequestDto(int page, int size) {
        setPage(page);
        setSize(size);
    }

    public PageRequest of() {
        return PageRequest.of(page -1, size);
    }
}