package com.youwiz.practices.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPassword is a Querydsl query type for Password
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QPassword extends BeanPath<Password> {

    private static final long serialVersionUID = -890272297L;

    public static final QPassword password = new QPassword("password");

    public final DateTimePath<java.time.LocalDateTime> expirationDate = createDateTime("expirationDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> failedCount = createNumber("failedCount", Integer.class);

    public final NumberPath<Long> ttl = createNumber("ttl", Long.class);

    public final StringPath value = createString("value");

    public QPassword(String variable) {
        super(Password.class, forVariable(variable));
    }

    public QPassword(Path<? extends Password> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPassword(PathMetadata metadata) {
        super(Password.class, metadata);
    }

}

