package com.youwiz.practices.service;

import com.youwiz.practices.domain.Delivery;
import com.youwiz.practices.domain.DeliveryStatus;
import com.youwiz.practices.dto.DeliveryDto;
import com.youwiz.practices.exception.DeliveryNotFoundException;
import com.youwiz.practices.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class DeliveryService {
    private DeliveryRepository deliveryRepository;

    public Delivery create(DeliveryDto.CreationReq dto) {
        final Delivery delivery = dto.toEntity();
        delivery.addLog(DeliveryStatus.PENDING);
        return deliveryRepository.save(delivery);
    }

    public Delivery updateStatus(long id, DeliveryDto.UpdateReq dto) {
        final Delivery delivery = findById(id);
        delivery.addLog(dto.getStatus());
        return delivery;
    }

    public Delivery findById(long id) {
        final Optional<Delivery> delivery = deliveryRepository.findById(id);
        delivery.orElseThrow(() -> new DeliveryNotFoundException(id));
        return delivery.get();
    }

    public Delivery removeLogs(long id) {
        final Delivery delivery = findById(id);
        delivery.getLogs().clear();
        return delivery;
    }

    public void remove(long id) {
        deliveryRepository.deleteById(id);
    }
}
