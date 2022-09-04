package com.example.billingservcice1.services;


import com.example.billingservcice1.Repositories.InvoiceRepository;
import com.example.billingservcice1.dto.InvoiceRequestDTO;
import com.example.billingservcice1.dto.InvoiceResponseDTO;
import com.example.billingservcice1.entities.Customer;
import com.example.billingservcice1.entities.Invoice;
import com.example.billingservcice1.mappers.InvoiceMapper;
import com.example.billingservcice1.openFeign.CustomerRestClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private InvoiceRepository invoiceRepository;
    private InvoiceMapper invoiceMapper;
    private CustomerRestClient customerRestClient;

//    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceM,CustomerRestClient customerR) {
//        this.invoiceRepository = invoiceRepository;
//        this.invoiceMapper = invoiceM;
//        this.customerRestClient = customerR;
//    }

    @Override
    public InvoiceResponseDTO save(InvoiceRequestDTO invoiceRequestDTO) {
            Invoice invoice=invoiceMapper.fromInvoiceRequestDTO(invoiceRequestDTO);
        try {
            invoice.setId(UUID.randomUUID().toString());
            invoice.setDate(new Date());
            invoice.setCustomerId(invoiceRequestDTO.getCustomerId());
            /*Verify customer exist*/
            Invoice savedInvoice = invoiceRepository.save(invoice);
            return invoiceMapper.fromInvoice(savedInvoice);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public InvoiceResponseDTO getInvoice(String invoiceId) {

        Invoice invoice=invoiceRepository.findById(invoiceId).get();
        Customer customer= customerRestClient.getCustomer(invoice.getCustomerId());
        invoice.setCustomer(customer);
        return invoiceMapper.fromInvoice(invoice);
    }

    @Override
    public List<InvoiceResponseDTO> invoiceByCustomerId(String customerId) {
        List<Invoice> invoices = invoiceRepository.findByCustomerId(customerId);
        for (Invoice inv : invoices){
            try{
                Customer customer=customerRestClient.getCustomer(inv.getCustomerId());
                inv.setCustomer(customer);

            }catch (Exception e){
                System.out.println("CUSTOMER NOT FOUND +++++++++++++++");
                System.out.println(e.getMessage());
            }
        }
        return invoices.stream().map(invoice -> invoiceMapper.fromInvoice(invoice)).collect(Collectors.toList());
    }

    @Override
    public List<InvoiceResponseDTO> allInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        for (Invoice inv : invoices){
            try{

            Customer customer=customerRestClient.getCustomer(inv.getCustomerId());
            inv.setCustomer(customer);

            }catch (Exception e){
                System.out.println("CUSTOMER NOT FOUND +++++++++++++++");
                System.out.println(e.getMessage());
            }
        }

        List<InvoiceResponseDTO> invoiceResponseDTOS = invoices.stream().map(invoice -> invoiceMapper.fromInvoice(invoice)).collect(Collectors.toList());
        return invoiceResponseDTOS;
    }
}

