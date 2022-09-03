package com.example.billingservcice1.services;

import com.example.billingservcice1.dto.InvoiceRequestDTO;
import com.example.billingservcice1.dto.InvoiceResponseDTO;

import java.util.List;

public interface InvoiceService {
    InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO);
    InvoiceResponseDTO getInvoice(String invoiceId);
    List<InvoiceResponseDTO> invoiceByCustomerId(String customerId);
}

