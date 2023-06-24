package com.jamanchi.hobby;

import com.jamanchi.hobby.dto.HobbyResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.jamanchi.hobby.QHobby.hobby;

@Repository
@RequiredArgsConstructor
public class HobbyRepository {

    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    public void save(Hobby entity){
        entityManager.persist(entity);
    }

    public Integer findIdByName(String name){
        return queryFactory
                .select(hobby.id)
                .from(hobby)
                .where(hobby.name.eq(name))
                .fetchOne();
    }

    public boolean existsByName(String name){
        return queryFactory
                .select(hobby.id)
                .from(hobby)
                .where(hobby.name.eq(name))
                .fetchFirst() != null;
    }

    public List<Hobby> findAll(){
        return queryFactory
                .selectFrom(hobby)
                .fetch();
    }

    public List<HobbyResponseDto.Info> findSubHobbies(Integer parentId){
        return queryFactory
                .select(Projections.constructor(HobbyResponseDto.Info.class,
                    hobby.id,
                    hobby.name,
                    hobby.parentId
                ))
                .from(hobby)
                .where(hobby.parentId.eq(parentId))
                .fetch();
    }

    public void deleteById(Integer id){
        queryFactory
                .delete(hobby)
                .where(hobby.id.eq(id))
                .execute();
    }
}
