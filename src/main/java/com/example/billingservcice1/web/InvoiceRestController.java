package com.example.billingservcice1.web;

import com.example.billingservcice1.dto.InvoiceRequestDTO;
import com.example.billingservcice1.dto.InvoiceResponseDTO;
import com.example.billingservcice1.services.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class InvoiceRestController {
    private InvoiceService invoiceService;
    @GetMapping(path = "/invoices/{id}")
    public InvoiceResponseDTO getInvoice(@PathVariable(name = "id")String invoiceId){
        return invoiceService.getInvoice(invoiceId);
    }
    @GetMapping(path = "/invoices/{customerId}")
    public List<InvoiceResponseDTO> getInvoicesCustomer(@PathVariable String customerId){
        return invoiceService.invoiceByCustomerId(customerId);
    }
    @PostMapping("/invoices")
    public InvoiceResponseDTO save(@RequestBody InvoiceRequestDTO invoiceRequestDTO){

        return invoiceService.save(invoiceRequestDTO);
    }
}
