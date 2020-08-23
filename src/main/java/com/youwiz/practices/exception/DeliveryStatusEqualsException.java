package com.youwiz.practices.exception;

import com.youwiz.practices.domain.DeliveryStatus;

public class DeliveryStatusEqualsException extends RuntimeException {

    private DeliveryStatus status;

    public DeliveryStatusEqualsException(DeliveryStatus status) {
        super(status.name() + " It can not be changed to the same state.");
        this.status = status;
    }
}
