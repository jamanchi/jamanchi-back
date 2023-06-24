package com.jamanchi.summary;

import com.jamanchi.commons.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Entity
@RequiredArgsConstructor
public class Summary extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "summary_id")
    private Long id;
    private String category;
    private Integer value;

    public void increseValue() {
        this.value = this.value+1;
    }
}
