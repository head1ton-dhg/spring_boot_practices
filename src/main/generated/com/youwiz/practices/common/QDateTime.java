package com.youwiz.practices.common;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDateTime is a Querydsl query type for DateTime
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QDateTime extends BeanPath<DateTime> {

    private static final long serialVersionUID = 1830125968L;

    public static final QDateTime dateTime = new QDateTime("dateTime");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QDateTime(String variable) {
        super(DateTime.class, forVariable(variable));
    }

    public QDateTime(Path<? extends DateTime> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDateTime(PathMetadata metadata) {
        super(DateTime.class, metadata);
    }

}

