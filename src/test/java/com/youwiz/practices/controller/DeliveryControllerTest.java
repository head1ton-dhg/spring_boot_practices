package com.youwiz.practices.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youwiz.practices.domain.Address;
import com.youwiz.practices.domain.DeliveryStatus;
import com.youwiz.practices.dto.DeliveryDto;
import com.youwiz.practices.error.ErrorExceptionController;
import com.youwiz.practices.service.DeliveryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class DeliveryControllerTest {

    @InjectMocks
    private DeliveryController deliveryController;

    @Mock
    private DeliveryService deliveryService;

    private ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(deliveryController)
                .setControllerAdvice(new ErrorExceptionController())
                .build();
    }

    @Test
    public void create() throws Exception {
        //given
        final Address address = buildAddress();
        final DeliveryDto.CreationReq dto = buildCreationDto(address);
        given(deliveryService.create(any())).willReturn(dto.toEntity());

        //when
        final ResultActions resultActions = requestCreate(dto);

        //then
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.address.address1", is(dto.getAddress().getAddress1())))
                .andExpect(jsonPath("$.address.address2", is(dto.getAddress().getAddress2())))
                .andExpect(jsonPath("$.address.zip", is(dto.getAddress().getZip())));
    }

    @Test
    public void getDelivery() throws Exception {
        //given
        final Address address = buildAddress();
        final DeliveryDto.CreationReq dto = buildCreationDto(address);
        given(deliveryService.findById(anyLong())).willReturn(dto.toEntity());

        //when
        final ResultActions resultActions = requestGetDelivery();

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address.address1", is(dto.getAddress().getAddress1())))
                .andExpect(jsonPath("$.address.address2", is(dto.getAddress().getAddress2())))
                .andExpect(jsonPath("$.address.zip", is(dto.getAddress().getZip())));
    }

    private ResultActions requestGetDelivery() throws Exception {
        return mockMvc.perform(get("/deliveries/" + anyLong())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    private ResultActions requestCreate(DeliveryDto.CreationReq dto) throws Exception {
        return mockMvc.perform(post("/delivers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andDo(print());
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