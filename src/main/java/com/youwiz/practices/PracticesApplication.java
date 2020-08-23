package com.youwiz.practices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(
        basePackageClasses = {Jsr310JpaConverters.class},
        basePackages = {"com"}
)
public class PracticesApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticesApplication.class, args);
    }

}
