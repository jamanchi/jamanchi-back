package com.jamanchi.keyword;

import com.jamanchi.keyword.dto.KeywordResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.jamanchi.keyword.QKeyword.keyword;

@Repository
@RequiredArgsConstructor
public class KeywordRepository {

    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    public void save(Keyword entity){
        entityManager.persist(entity);
    }

    public List<KeywordResponseDto.Info> getKeywords(){
        return queryFactory
                .select(Projections.constructor(KeywordResponseDto.Info.class,
                        keyword.id,
                        keyword.name
                ))
                .from(keyword)
                .fetch();
    }
}
