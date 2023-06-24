package com.jamanchi.keyword;

import com.jamanchi.commons.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Keyword extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_id")
    private Integer id;

    @Column(nullable = false)
    private String name;

    public Keyword(String name){
        this.name = name;
    }
}
