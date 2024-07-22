package com.api.clinica;

import java.util.Locale;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClinicaApplication {

    public static void main(String[] args) {
        Locale.setDefault(Locale.of("pt", "BR"));
        SpringApplication.run(ClinicaApplication.class, args);
    }
}
