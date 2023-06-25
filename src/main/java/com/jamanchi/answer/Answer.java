package com.jamanchi.answer;

import com.jamanchi.commons.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class Answer extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "answer_id")
    private Integer id;
    @NotNull
    private Integer hobby_id;
    @NotNull
    private Integer keyword_id;
    private String contents;

    public Answer(Integer hobby_id, Integer keyword_id, String contents) {
        this.hobby_id = hobby_id;
        this.keyword_id = keyword_id;
        this.contents = contents;
    }
}
