package com.jamanchi.answer;

import com.jamanchi.commons.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
public class Answer extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "answer_id")
    private Long id;
    @NotNull
    private Long hobby_id;
    @NotNull
    private Long keyword_id;
    private String contents;
}
