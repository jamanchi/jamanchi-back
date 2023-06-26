package com.jamanchi.hobby;

import com.jamanchi.hobby.dto.HobbyResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    public Hobby findOne(Integer hobbyId){
        return queryFactory
                .selectFrom(hobby)
                .where(hobby.id.eq(hobbyId))
                .fetchOne();
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

    // 전체 대분류 취미 조회
    public List<HobbyResponseDto.Info> findAllMainHobbies(){
        return queryFactory
                .select(Projections.constructor(HobbyResponseDto.Info.class,
                        hobby.id,
                        hobby.name,
                        hobby.image
                ))
                .from(hobby)
                .where(hobby.parentId.eq(0))
                .fetch();
    }

    public Page<HobbyResponseDto.Info> findAllSubHobbies(Pageable pageable){
        List<HobbyResponseDto.Info> contents = queryFactory
                .select(Projections.constructor(HobbyResponseDto.Info.class,
                        hobby.id,
                        hobby.name,
                        hobby.image
                ))
                .from(hobby)
                .where(hobby.parentId.ne(0))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .groupBy(hobby.name)
                .orderBy(hobby.id.asc())
                .fetch();

        List<Integer> total = queryFactory
                .select(hobby.id)
                .from(hobby)
                .where(hobby.parentId.ne(0))
                .groupBy(hobby.name)
                .fetch();

        return new PageImpl<>(contents, pageable, total.size());
    }

    public List<HobbyResponseDto.Info> findSubHobbies(Integer parentId){
        return queryFactory
                .select(Projections.constructor(HobbyResponseDto.Info.class,
                    hobby.id,
                    hobby.name,
                    hobby.image
                ))
                .from(hobby)
                .where(hobby.parentId.eq(parentId))
                .groupBy(hobby.name)
                .fetch();
    }

    public List<HobbyResponseDto.Info> findRecommendHobbies(String recommendId){
        return queryFactory
                .select(Projections.constructor(HobbyResponseDto.Info.class,
                        hobby.id,
                        hobby.name,
                        hobby.image
                ))
                .from(hobby)
                .where(hobby.recommendId.eq(recommendId))
                .fetch();
    }

    public void updateImage(String name, String image){
        queryFactory
                .update(hobby)
                .set(hobby.image, image)
                .where(hobby.name.eq(name))
                .execute();
    }

    public void deleteById(Integer id){
        queryFactory
                .delete(hobby)
                .where(hobby.id.eq(id))
                .execute();
    }
}
