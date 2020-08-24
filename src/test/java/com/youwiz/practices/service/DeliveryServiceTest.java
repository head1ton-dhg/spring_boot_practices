package com.youwiz.practices.service;

import com.youwiz.practices.domain.Address;
import com.youwiz.practices.domain.Delivery;
import com.youwiz.practices.domain.DeliveryStatus;
import com.youwiz.practices.dto.DeliveryDto;
import com.youwiz.practices.exception.DeliveryNotFoundException;
import com.youwiz.practices.repository.DeliveryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static java.util.OptionalDouble.empty;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DeliveryServiceTest {

    @InjectMocks
    private DeliveryService deliveryService;

    @Mock
    private DeliveryRepository deliveryRepository;

    @Test
    public void create() {
        //given
        final Address address = buildAddress();
        final DeliveryDto.CreationReq dto = buildCreationDto(address);

        given(deliveryRepository.save(any(Delivery.class))).willReturn(dto.toEntity());

        //when
        final Delivery delivery = deliveryService.create(dto);

        //then
        assertEquals(delivery.getAddress(), address);
    }

    @Test
    public void updateStatus() {
        //given
        final Address address = buildAddress();
        final DeliveryDto.CreationReq creationReq = buildCreationDto(address);
        final DeliveryDto.UpdateReq updateReq = buildUpdateReqDto();

        given(deliveryRepository.findById(anyLong())).willReturn(Optional.of(creationReq.toEntity()));

        //when
        final Delivery delivery = deliveryService.updateStatus(anyLong(), updateReq);

        //then
        assertEquals(delivery.getAddress(), address);
        assertEquals(delivery.getLogs().get(0).getStatus(), updateReq.getStatus());
    }

    @Test
    public void findById() {
        //given
        final Address address = buildAddress();
        final DeliveryDto.CreationReq dto = buildCreationDto(address);
        given(deliveryRepository.findById(anyLong())).willReturn(Optional.of(dto.toEntity()));

        //when
        final Delivery delivery = deliveryService.findById(anyLong());

        //then
        assertEquals(delivery.getAddress(), address);
    }

    @Test
    public void findById_존재하지않을경우_DeliveryNotFoundException() {
        assertThrows(DeliveryNotFoundException.class, () -> {
            //given
            final Address address = buildAddress();
            final DeliveryDto.CreationReq dto = buildCreationDto(address);

            given(deliveryRepository.findById(anyLong())).willReturn(Optional.empty());

            //when
            deliveryService.findById(anyLong());
        });
    }

    @Test
    public void removeLogs() {
        //given
        final Address address = buildAddress();
        final DeliveryDto.CreationReq dto = buildCreationDto(address);
        given(deliveryRepository.findById(anyLong())).willReturn(Optional.of(dto.toEntity()));

        //when
        final Delivery delivery = deliveryService.removeLogs(anyLong());

        //then
        assertEquals(delivery.getLogs(), empty());
    }

    @Test
    public void remove() {
        deliveryService.remove(anyLong());

        verify(deliveryRepository, atLeastOnce()).deleteById(anyLong());
    }


    private DeliveryDto.UpdateReq buildUpdateReqDto() {
        return DeliveryDto.UpdateReq.builder()
                .status(DeliveryStatus.DELIVERING)
                .build();
    }

    private DeliveryDto.CreationReq buildCreationDto(Address address) {
        return DeliveryDto.CreationReq.builder()
                .address(address)
                .build();
    }

    private Address buildAddress() {
        return Address.builder()
                .address1("address1...")
                .address2("address2...")
                .zip("zip...")
                .build();
    }


}