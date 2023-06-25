package com.jamanchi.question;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.jamanchi.question.QQuestion.question1;


@Repository
@RequiredArgsConstructor
public class QuestionRepository {

    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    public void save(Question entity){
        entityManager.persist(entity);
    }

    public List<String> getAll(){
        return queryFactory
                .select(question1.question)
                .from(question1)
                .fetch();
    }

    public void deleteAll(){
        queryFactory
                .delete(question1)
                .execute();
    }
}
