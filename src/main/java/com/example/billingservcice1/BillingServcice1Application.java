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
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(98000),"C01"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(98400),"C01"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(28300),"C03"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(48050),"C04"));
            invoiceService.save(new InvoiceRequestDTO(BigDecimal.valueOf(58050),"C05"));
        };
    }
}
