package com.projects.InvoiceManagementSystem;
import com.projects.InvoiceManagementSystem.DTO.InvoiceDTO;
import com.projects.InvoiceManagementSystem.DTO.InvoiceHistoryDTO;
import com.projects.InvoiceManagementSystem.DTO.InvoiceItemDTO;
import com.projects.InvoiceManagementSystem.DTO.UserDetailDTO;
import com.projects.InvoiceManagementSystem.Entities.Invoice;
import com.projects.InvoiceManagementSystem.Entities.InvoiceHistory;
import com.projects.InvoiceManagementSystem.Entities.InvoiceItem;
import com.projects.InvoiceManagementSystem.Entities.UserDetail;
import com.projects.InvoiceManagementSystem.Repository.InvoiceHistoryRepository;
import com.projects.InvoiceManagementSystem.Repository.InvoiceItemRepository;
import com.projects.InvoiceManagementSystem.Repository.InvoiceRepository;
import com.projects.InvoiceManagementSystem.Repository.UserDetailRepository;
import com.projects.InvoiceManagementSystem.Service.Implementions.InvoiceHistoryImplementation;
import com.projects.InvoiceManagementSystem.Service.Implementions.InvoiceItemServiceImplementation;
import com.projects.InvoiceManagementSystem.Service.Implementions.InvoiceServiceImplementation;
import com.projects.InvoiceManagementSystem.Service.Implementions.UserDetailsImplementation;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class InvoiceManagementSystemApplicationTests {

//    User Details testing
    @Mock
    private UserDetailRepository userDetailRepository;

    @InjectMocks
    private UserDetailsImplementation userDetailService;

    @Mock
    private InvoiceRepository invoiceRepository;

    @InjectMocks
    private InvoiceServiceImplementation invoiceService;

    @Mock
    private InvoiceItemRepository invoiceItemRepository;

    @InjectMocks
    private InvoiceItemServiceImplementation invoiceItemService;

    @Mock
    private InvoiceHistoryRepository invoiceHistoryRepository;

    @InjectMocks
    private InvoiceHistoryImplementation invoiceHistoryImplementation;

    @Test
    void testGetAllUsersTest() {
        UserDetail user = new UserDetail();
        user.setUserId(UUID.randomUUID());
        user.setUserName("Radhe Ravi");
        user.setStatus("Active");

        UserDetail user1 = new UserDetail();
        user1.setUserId(UUID.randomUUID());
        user1.setUserName("Ashish");
        user1.setStatus("Active");

        UserDetail user2 = new UserDetail();
        user2.setUserId(UUID.randomUUID());
        user2.setUserName("Kishan");
        user2.setStatus("Active");
        List<UserDetail> userList = new ArrayList<>(List.of(user, user1, user2));

        when(userDetailRepository.findAll()).thenReturn(userList);

        List<UserDetailDTO> result = userDetailService.getAllUsers();
        assertEquals(3, result.size());
        assertEquals("Radhe Ravi", result.get(0).getUserName());
        assertEquals("Active", result.get(0).getStatus());
    }

    @Test
    void getUserByIdTest() {
        UUID USER_ID = UUID.randomUUID();
        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(USER_ID);
        userDetail.setUserName("Radhe Ravi");
        userDetail.setStatus("Active");
        userDetailRepository.save(userDetail);

        when(userDetailRepository.findById(USER_ID)).thenReturn(Optional.of(userDetail));

        UserDetailDTO result = userDetailService.getUserById(USER_ID);
        assertEquals("Radhe Ravi", result.getUserName());
        assertEquals("Active", result.getStatus());

    }

    @Test
    void createUserTest(){
        UserDetailDTO userDetailDTO = new UserDetailDTO();
        userDetailDTO.setUserName("Radhe Ravi");
        userDetailDTO.setStatus("Offline");

        when(userDetailRepository.save(any(UserDetail.class))).thenReturn(new UserDetail());

        UserDetailDTO result = userDetailService.createUser(userDetailDTO);
        assertNotNull(result.getUserId());
    }


//    invoice
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


    // invoice item



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

    // History


    @Test
    void testGetAllInvoiceHistory() {
        UUID id = UUID.randomUUID();
        UserDetail userDetail = new UserDetail();
        userDetail.setUserName("Radhe Ravi");
        userDetail.setUserId(id);


        InvoiceHistory invoiceHistory = new InvoiceHistory();
        invoiceHistory.setInvoiceId(id);
        invoiceHistory.setEvent("Created");
        invoiceHistory.setCreatedBy(userDetail);

        InvoiceHistory invoiceHistory1 = new InvoiceHistory();
        invoiceHistory1.setInvoiceId(id);
        invoiceHistory1.setEvent("Created");
        invoiceHistory1.setCreatedBy(userDetail);


        List<InvoiceHistory> invoiceHistoryList = new ArrayList<>(List.of(invoiceHistory1,invoiceHistory));
        when(invoiceHistoryRepository.findAll()).thenReturn(invoiceHistoryList);

        List<InvoiceHistoryDTO> result = invoiceHistoryImplementation.getAllInvoiceHistories();
        assertEquals(2, result.size());
        assertEquals("Created", result.get(0).getEvent());
    }



    @Test
    void testGetInvoiceHistoryById(){
        UUID id = UUID.randomUUID();
        UserDetail userDetail = new UserDetail();
        userDetail.setUserName("Radhe Ravi");
        userDetail.setUserId(id);

        UUID invoiceId = UUID.randomUUID();
        InvoiceHistory invoiceHistory = new InvoiceHistory();
        invoiceHistory.setInvoiceId(invoiceId);
        invoiceHistory.setEvent("Event1");
        invoiceHistory.setCreatedBy(userDetail);

        when(invoiceHistoryRepository.findById(invoiceId)).thenReturn(Optional.of(invoiceHistory));

        InvoiceHistoryDTO result = invoiceHistoryImplementation.getInvoiceHistoryById(invoiceId);
        assertEquals("Event1", result.getEvent());

    }

    @Test
    void testDeleteInvoiceHistory() {
        UUID invoiceId = UUID.randomUUID();

        doNothing().when(invoiceHistoryRepository).deleteById(invoiceId);
        invoiceHistoryImplementation.deleteInvoiceHistory(invoiceId);
        verify(invoiceHistoryRepository, times(1)).deleteById(invoiceId);
    }

    @Test
    void contextLoads() {
    }
}
