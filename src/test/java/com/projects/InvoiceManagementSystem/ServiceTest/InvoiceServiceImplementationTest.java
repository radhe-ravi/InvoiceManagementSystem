package com.projects.InvoiceManagementSystem.ServiceTest;

import com.projects.InvoiceManagementSystem.DTO.InvoiceDTO;
import com.projects.InvoiceManagementSystem.Entities.Invoice;
import com.projects.InvoiceManagementSystem.Entities.UserDetail;
import com.projects.InvoiceManagementSystem.Repository.InvoiceRepository;
import com.projects.InvoiceManagementSystem.Service.Implementions.InvoiceServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class InvoiceServiceImplementationTest {
    @Mock
    private InvoiceRepository invoiceRepository;

    @InjectMocks
    private InvoiceServiceImplementation invoiceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void testGetAllInvoices() {

        UUID USER_ID = UUID.randomUUID();
        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(USER_ID);
        userDetail.setUserName("Radhe Ravi");
        userDetail.setStatus("Active");


        List<Invoice> invoices = new ArrayList<>();
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(UUID.randomUUID());
        invoice.setInvoiceNo("XMX7079");
        invoice.setCreatedBy(userDetail);
        invoices.add(invoice);

        when(invoiceRepository.findAll()).thenReturn(invoices);

        List<InvoiceDTO> result = invoiceService.getAllInvoices();
        assertEquals(1,result.size());
        assertEquals("XMX7079",result.get(0).getInvoiceNo());
    }


    @Test
    void testGetInvoiceById() {
        UUID USER_ID = UUID.randomUUID();
        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(USER_ID);
        userDetail.setUserName("Radhe Ravi");
        userDetail.setStatus("Active");

        Invoice invoice = new Invoice();
        invoice.setInvoiceId(USER_ID);
        invoice.setInvoiceNo("XMX7079");
        invoice.setCreatedBy(userDetail);


        when(invoiceRepository.findById(USER_ID)).thenReturn(Optional.of(invoice));

        InvoiceDTO result = invoiceService.getInvoiceById(USER_ID);
        assertEquals("XMX7079",result.getInvoiceNo());
    }

    @Test
    void testCreateInvoice() {
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setInvoiceNo("20BCS3021");
        invoiceDTO.setPanNo("XMX2514461");
        invoiceDTO.setGstNo("12ABCDE3456F7Z8");
        invoiceDTO.setCreatedBy(UUID.randomUUID());

        Invoice invoice = new Invoice();
        invoice.setInvoiceId(UUID.randomUUID());
        invoice.setInvoiceNo("INV123");
        invoice.setPanNo("XMX2514461");
        invoice.setGstNo("12ABCDE3456F7Z8");

        when(invoiceRepository.save(any(Invoice.class))).thenReturn(invoice);

        InvoiceDTO result = invoiceService.createInvoice(invoiceDTO);
        assertNotNull(result.getInvoiceId());
        assertEquals("20BCS3021", result.getInvoiceNo());
    }

    @Test
    void testUpdateInvoice() {
        UUID invoiceId = UUID.randomUUID();
        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(invoiceId);
        userDetail.setUserName("Radhe Ravi");
        userDetail.setStatus("Active");


        Invoice invoice = new Invoice();
        invoice.setInvoiceId(invoiceId);
        invoice.setInvoiceNo("20BCS3023");
        invoice.setPanNo("ABCDE1234F");
        invoice.setGstNo("12ABCDE3456F7Z8");
        invoice.setCreatedBy(userDetail);



        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setInvoiceNo("20BCS3021");
        invoiceDTO.setPanNo("XMX2514461");
        invoiceDTO.setGstNo("98XYZ3456F7Z8");

        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(invoice);

        InvoiceDTO result = invoiceService.updateInvoice(invoiceId, invoiceDTO);
        assertEquals("20BCS3021", result.getInvoiceNo());
        assertEquals("XMX2514461", result.getPanNo());
        assertEquals("98XYZ3456F7Z8", result.getGstNo());
    }

    @Test
    void testDeleteInvoice() {
        UUID invoiceId = UUID.randomUUID();

        doNothing().when(invoiceRepository).deleteById(invoiceId);

        invoiceService.deleteInvoice(invoiceId);

        verify(invoiceRepository, times(1)).deleteById(invoiceId);
    }
}
