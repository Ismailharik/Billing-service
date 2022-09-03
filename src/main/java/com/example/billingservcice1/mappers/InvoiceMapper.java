package com.example.billingservcice1.mappers;

import com.example.billingservcice1.dto.InvoiceRequestDTO;
import com.example.billingservcice1.dto.InvoiceResponseDTO;
import com.example.billingservcice1.entities.Invoice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    Invoice fromInvoiceRequestDTO(InvoiceRequestDTO invoiceRequestDTO);
    InvoiceResponseDTO fromInvoice(Invoice invoice);
}
