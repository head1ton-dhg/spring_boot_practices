package com.youwiz.practices.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDeliveryLog is a Querydsl query type for DeliveryLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDeliveryLog extends EntityPathBase<DeliveryLog> {

    private static final long serialVersionUID = 1264498164L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDeliveryLog deliveryLog = new QDeliveryLog("deliveryLog");

    public final com.youwiz.practices.common.QDateTime dateTime;

    public final QDelivery delivery;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<DeliveryStatus> status = createEnum("status", DeliveryStatus.class);

    public QDeliveryLog(String variable) {
        this(DeliveryLog.class, forVariable(variable), INITS);
    }

    public QDeliveryLog(Path<? extends DeliveryLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDeliveryLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDeliveryLog(PathMetadata metadata, PathInits inits) {
        this(DeliveryLog.class, metadata, inits);
    }

    public QDeliveryLog(Class<? extends DeliveryLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.dateTime = inits.isInitialized("dateTime") ? new com.youwiz.practices.common.QDateTime(forProperty("dateTime")) : null;
        this.delivery = inits.isInitialized("delivery") ? new QDelivery(forProperty("delivery"), inits.get("delivery")) : null;
    }

}

