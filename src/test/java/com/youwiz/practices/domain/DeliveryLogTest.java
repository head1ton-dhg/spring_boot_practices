package com.youwiz.practices.domain;

import com.youwiz.practices.dto.DeliveryDto;
import com.youwiz.practices.exception.DeliveryAlreadyDeliveringException;
import com.youwiz.practices.exception.DeliveryStatusEqualsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class DeliveryLogTest {

    @Test
    public void delivery_pending_로그저장() {
        final DeliveryStatus status = DeliveryStatus.PENDING;
        final DeliveryLog log = buildLog(buildDelivery(), status);

        assertEquals(status, log.getStatus());

        // 커버리지 높이기 위한 임시함수
        log.getDateTime();
        log.getDelivery();
        log.getLastStatus();

        DeliveryDto.LogRes logRes = new DeliveryDto.LogRes(log);

        logRes.getDateTime();
        logRes.getStatus();
    }

    @Test
    public void delivery_delivering() {
        final Delivery delivery = buildDelivery();
        final DeliveryStatus status = DeliveryStatus.PENDING;

        delivery.addLog(status);
        delivery.addLog(DeliveryStatus.DELIVERING);
    }

    @Test
    public void delivery_canceled() {
        final Delivery delivery = buildDelivery();
        final DeliveryStatus status = DeliveryStatus.PENDING;

        delivery.addLog(status);
        delivery.addLog(DeliveryStatus.CANCELED);
    }

    @Test
    public void delivery_completed() {
        final Delivery delivery = buildDelivery();
        final DeliveryStatus status = DeliveryStatus.DELIVERING;

        delivery.addLog(status);
        delivery.addLog(DeliveryStatus.COMPLETED);
    }

    @Test
    public void 동일한_status_변경시_DeliveryStatusEqualsException() {
        assertThrows(DeliveryStatusEqualsException.class, () -> {
            final Delivery delivery = buildDelivery();
            final DeliveryStatus status = DeliveryStatus.DELIVERING;

            delivery.addLog(status);
            delivery.addLog(DeliveryStatus.DELIVERING);
        });
    }

    @Test
    public void 배송시작후_취소시_DeliveryAlreadyDeliveringException() {
        assertThrows(DeliveryAlreadyDeliveringException.class, () -> {
            final Delivery delivery = buildDelivery();
            final DeliveryStatus status = DeliveryStatus.DELIVERING;

            delivery.addLog(status);
            delivery.addLog(DeliveryStatus.CANCELED);
        });
    }

    @Test
    public void 완료상태_변경시_IllegalArugmentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            final Delivery delivery = buildDelivery();
            final DeliveryStatus status = DeliveryStatus.COMPLETED;

            delivery.addLog(status);
            delivery.addLog(DeliveryStatus.CANCELED);
        });
    }


    private DeliveryLog buildLog(Delivery delivery, DeliveryStatus status) {
        return DeliveryLog.builder()
                .delivery(delivery)
                .status(status)
                .build();
    }

    private Delivery buildDelivery() {
        return Delivery.builder()
                .build();
    }
}