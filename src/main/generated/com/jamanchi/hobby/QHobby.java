package com.jamanchi.hobby;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHobby is a Querydsl query type for Hobby
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHobby extends EntityPathBase<Hobby> {

    private static final long serialVersionUID = 713098102L;

    public static final QHobby hobby = new QHobby("hobby");

    public final com.jamanchi.commons.QBaseEntity _super = new com.jamanchi.commons.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final NumberPath<Integer> parentId = createNumber("parentId", Integer.class);

    public QHobby(String variable) {
        super(Hobby.class, forVariable(variable));
    }

    public QHobby(Path<? extends Hobby> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHobby(PathMetadata metadata) {
        super(Hobby.class, metadata);
    }

}

