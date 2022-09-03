package com.example.billingservcice1;

import com.example.billingservcice1.dto.InvoiceRequestDTO;
import com.example.billingservcice1.services.InvoiceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@EnableFeignClients
public class BillingServcice1Application {

    public static void main(String[] args) {
        SpringApplication.run(BillingServcice1Application.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(InvoiceService invoiceService){
        return args -> {
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(98000),"01"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(98000),"02"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(98000),"03"));
        };
    }
}
