package com.jamanchi.answer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAnswer is a Querydsl query type for Answer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAnswer extends EntityPathBase<Answer> {

    private static final long serialVersionUID = 1132755182L;

    public static final QAnswer answer = new QAnswer("answer");

    public final com.jamanchi.commons.QBaseEntity _super = new com.jamanchi.commons.QBaseEntity(this);

    public final StringPath contents = createString("contents");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> hobby_id = createNumber("hobby_id", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> keyword_id = createNumber("keyword_id", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public QAnswer(String variable) {
        super(Answer.class, forVariable(variable));
    }

    public QAnswer(Path<? extends Answer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAnswer(PathMetadata metadata) {
        super(Answer.class, metadata);
    }

}

