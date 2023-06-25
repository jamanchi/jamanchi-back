package com.jamanchi.answer;

import com.jamanchi.answer.dto.AnswerResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.jamanchi.answer.QAnswer.answer;

@Repository
@RequiredArgsConstructor
public class AnswerRepository {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    public void save(Answer entity){
        entityManager.persist(entity);
    }

    public AnswerResponseDto getAnswer(Integer hobbyId, Integer keywordId){
        return queryFactory
                .select(Projections.constructor(AnswerResponseDto.class,
                        answer.id,
                        answer.hobby_id,
                        answer.keyword_id,
                        answer.contents
                ))
                .from(answer)
                .where(answer.hobby_id.eq(hobbyId), answer.keyword_id.eq(keywordId))
                .fetchOne();
    }
}
