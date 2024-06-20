package com.projects.InvoiceManagementSystem.ServiceTest;

import static org.mockito.ArgumentMatchers.any;
import com.projects.InvoiceManagementSystem.DTO.InvoiceItemDTO;
import com.projects.InvoiceManagementSystem.Entities.Invoice;
import com.projects.InvoiceManagementSystem.Entities.InvoiceItem;
import com.projects.InvoiceManagementSystem.Entities.UserDetail;
import com.projects.InvoiceManagementSystem.Repository.InvoiceItemRepository;
import com.projects.InvoiceManagementSystem.Service.Implementions.InvoiceItemServiceImplementation;
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
import static org.mockito.Mockito.*;


class InvoiceItemServiceImplementationTest {

    @Mock
    private InvoiceItemRepository invoiceItemRepository;

    @InjectMocks
    private InvoiceItemServiceImplementation invoiceItemService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllInvoiceItems(){
        UUID USER_ID = UUID.randomUUID();
        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(USER_ID);
        userDetail.setUserName("Radhe Ravi");
        userDetail.setStatus("Active");

        Invoice invoice = new Invoice();
        invoice.setInvoiceId(USER_ID);
        invoice.setInvoiceNo("XMX7079");
        invoice.setCreatedBy(userDetail);


        List<InvoiceItem> invoiceItemList = new ArrayList<>();
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceItemId(USER_ID);
        invoiceItem.setItemName("Samsung M52 5g");
        invoiceItem.setItemDescription("This is android phone in budget range by samsung in just 25000");
        invoiceItem.setInvoice(invoice);
        invoiceItem.setCreatedBy(userDetail);
        invoiceItemList.add(invoiceItem);

        when(invoiceItemRepository.findAll()).thenReturn(invoiceItemList);

        List<InvoiceItemDTO> result = invoiceItemService.getAllInvoiceItems();
        assertEquals(1, result.size());
        assertEquals("Samsung M52 5g", result.get(0).getItemName());
    }


    @Test
    void testGetInvoiceItemById(){
        UUID USER_ID = UUID.randomUUID();
        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(USER_ID);
        userDetail.setUserName("Radhe Ravi");
        userDetail.setStatus("Active");

        Invoice invoice = new Invoice();
        invoice.setInvoiceId(USER_ID);
        invoice.setInvoiceNo("XMX7079");
        invoice.setCreatedBy(userDetail);


        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceItemId(USER_ID);
        invoiceItem.setItemName("Samsung M52 5g");
        invoiceItem.setItemDescription("This is android phone in budget range by samsung in just 25000");
        invoiceItem.setInvoice(invoice);
        invoiceItem.setCreatedBy(userDetail);

        when(invoiceItemRepository.findById(USER_ID)).thenReturn(Optional.of(invoiceItem));

        InvoiceItemDTO result = invoiceItemService.getInvoiceItemById(USER_ID);

        assertEquals("Samsung M52 5g", result.getItemName());
    }

    @Test
    void testCreateInvoiceItem(){
        InvoiceItemDTO invoiceItemDTO = new InvoiceItemDTO();
        invoiceItemDTO.setItemName("Samsung M52 5g");
        invoiceItemDTO.setItemDescription("This is android phone in budget range by samsung in just 25000");
        invoiceItemDTO.setInvoiceId(UUID.randomUUID());
        invoiceItemDTO.setCreatedBy(UUID.randomUUID());

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceItemId(UUID.randomUUID());
        invoiceItem.setItemName("Samsung M52 5g");
        invoiceItem.setItemDescription("This is android phone in budget range by samsung in just 25000");
        invoiceItem.setInvoice(new Invoice(invoiceItemDTO.getInvoiceId()));
        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(invoiceItemDTO.getCreatedBy());
        invoiceItem.setCreatedBy(userDetail);

        when(invoiceItemRepository.save(any(InvoiceItem.class))).thenReturn(invoiceItem);

        InvoiceItemDTO result = invoiceItemService.createInvoiceItem(invoiceItemDTO);
        assertNotNull(result.getInvoiceItemId());
        assertEquals("Samsung M52 5g", result.getItemName());
    }

    @Test
    void testUpdateInvoiceItem(){
        UUID invoiceItemId = UUID.randomUUID();
        InvoiceItemDTO updatedInvoiceItemDTO = new InvoiceItemDTO();
        updatedInvoiceItemDTO.setItemName("Samsung M52 5g");
        updatedInvoiceItemDTO.setItemDescription("This is android phone in budget range by samsung in just 25000 and it is a 5g variant");
        UUID invoiceId = UUID.randomUUID();
        updatedInvoiceItemDTO.setInvoiceId(invoiceId);
        updatedInvoiceItemDTO.setCreatedBy(UUID.randomUUID());

        InvoiceItem existingInvoiceItem = new InvoiceItem();
        existingInvoiceItem.setInvoiceItemId(invoiceItemId);
        existingInvoiceItem.setItemName("Samsung M52");
        existingInvoiceItem.setItemDescription("This is android phone in budget range by samsung in just 25000");
        existingInvoiceItem.setInvoice(new Invoice(invoiceId));
        existingInvoiceItem.setCreatedBy(new UserDetail());


        when(invoiceItemRepository.findById(invoiceItemId)).thenReturn(Optional.of(existingInvoiceItem));
        when(invoiceItemRepository.save(any(InvoiceItem.class))).thenReturn(existingInvoiceItem);


        InvoiceItemDTO result = invoiceItemService.updateInvoiceItem(invoiceItemId, updatedInvoiceItemDTO);


        assertNotNull(result.getInvoiceItemId());
        assertEquals("Samsung M52 5g", result.getItemName());
        assertEquals("This is android phone in budget range by samsung in just 25000 and it is a 5g variant", result.getItemDescription());
        assertEquals(invoiceId, result.getInvoiceId());
        assertEquals(existingInvoiceItem.getCreatedBy().getUserId(), result.getCreatedBy());

        verify(invoiceItemRepository, times(1)).findById(invoiceItemId);
        verify(invoiceItemRepository, times(1)).save(existingInvoiceItem);
    }

    @Test
    void testDeleteInvoiceItem(){
        UUID invoiceItemId = UUID.randomUUID();

        doNothing().when(invoiceItemRepository).deleteById(invoiceItemId);

        invoiceItemService.deleteInvoiceItem(invoiceItemId);

        verify(invoiceItemRepository, times(1)).deleteById(invoiceItemId);
    }

}
