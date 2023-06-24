package com.jamanchi.summary;

import com.jamanchi.commons.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Summary extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "summary_id")
    private Long id;
    private String category;
    private Integer value;
}
