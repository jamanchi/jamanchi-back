package com.jamanchi.answer;

import com.jamanchi.answer.dto.AnswerResponseDto;
import com.jamanchi.answer.dto.AnswerResultDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.jamanchi.answer.QAnswer.answer;
import static com.jamanchi.hobby.QHobby.hobby;
import static com.jamanchi.keyword.QKeyword.keyword;

@Repository
@RequiredArgsConstructor
public class AnswerRepository {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    public void save(Answer entity){
        entityManager.persist(entity);
    }

    public boolean existsById(Integer hobbyId, Integer keywordId){
        return queryFactory
                .select(answer.id)
                .from(answer)
                .where(answer.hobby_id.eq(hobbyId), answer.keyword_id.eq(keywordId))
                .fetchFirst() != null;
    }

    public AnswerResultDto getAnswer2(Integer hobbyId, Integer keywordId){
        return queryFactory
                .select(Projections.constructor(AnswerResultDto.class,
                        keyword.name,
                        answer.contents
                ))
                .from(answer)
                .join(keyword).on(keyword.id.eq(keywordId)).fetchJoin()
                .where(answer.hobby_id.eq(hobbyId), answer.keyword_id.eq(keywordId))
                .fetchOne();
    }

    public AnswerResponseDto.GptResponse getAnswer(Integer hobbyId, Integer keywordId){
        return queryFactory
                .select(Projections.constructor(AnswerResponseDto.GptResponse.class,
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
