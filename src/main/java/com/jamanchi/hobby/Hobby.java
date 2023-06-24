package com.jamanchi.hobby;

import com.jamanchi.commons.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Hobby extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hobby_id")
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(name = "parent_id")
    private Integer parentId;

    public Hobby(String name, Integer parentId){
        this.name = name;
        this.parentId = parentId;
    }
}
