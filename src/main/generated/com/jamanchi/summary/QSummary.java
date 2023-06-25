package com.jamanchi.summary;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSummary is a Querydsl query type for Summary
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSummary extends EntityPathBase<Summary> {

    private static final long serialVersionUID = -467619298L;

    public static final QSummary summary = new QSummary("summary");

    public final com.jamanchi.commons.QBaseEntity _super = new com.jamanchi.commons.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final NumberPath<Long> value = createNumber("value", Long.class);

    public QSummary(String variable) {
        super(Summary.class, forVariable(variable));
    }

    public QSummary(Path<? extends Summary> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSummary(PathMetadata metadata) {
        super(Summary.class, metadata);
    }

}

