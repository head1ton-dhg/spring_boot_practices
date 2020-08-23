package com.youwiz.practices.domain;

import com.youwiz.practices.common.DateTime;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "delivery")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<DeliveryLog> logs = new ArrayList<>();

    @Embedded
    private DateTime datetime;

    @Builder
    public Delivery(Address address) {
        this.address = address;
    }

    public void addLog(DeliveryStatus status) {
        this.logs.add(buildLog(status));
    }

    private DeliveryLog buildLog(DeliveryStatus status) {
        return DeliveryLog.builder()
                .status(status)
                .delivery(this)
                .build();
    }
}
