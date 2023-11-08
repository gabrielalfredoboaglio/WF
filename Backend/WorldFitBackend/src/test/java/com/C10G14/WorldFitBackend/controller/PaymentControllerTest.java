package com.C10G14.WorldFitBackend.controller;

import com.C10G14.WorldFitBackend.dto.payment.PaymentDto;
import com.C10G14.WorldFitBackend.security.jwt.JwtService;
import com.C10G14.WorldFitBackend.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = PaymentController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    @MockBean
    private JwtService jwtService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddPayment() throws Exception {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmount(100.0);
        paymentDto.setDescription("Test payment");

        when(paymentService.createPayment(paymentDto)).thenReturn(paymentDto);

        mockMvc.perform(post("/api/v1/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(paymentDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.amount", Matchers.is(100.0)))
                .andExpect(jsonPath("$.description", Matchers.is("Test payment")));

        verify(paymentService).createPayment(paymentDto);
    }

    @Test
    public void testGetPaymentById() throws Exception {
        Long id = 1L;
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmount(100.0);
        paymentDto.setDescription("Test payment");

        when(paymentService.getPaymentById(id)).thenReturn(paymentDto);

        mockMvc.perform(get("/api/v1/payments/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.amount", Matchers.is(100.0)))
                .andExpect(jsonPath("$.description", Matchers.is("Test payment")));

        verify(paymentService).getPaymentById(id);
    }

    @Test
    public void testUpdatePayment() throws Exception {
        Long id = 1L;
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmount(200.0);
        paymentDto.setDescription("Updated payment");

        when(paymentService.updatePayment(id, paymentDto)).thenReturn(paymentDto);

        mockMvc.perform(put("/api/v1/payments/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(paymentDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.amount", Matchers.is(200.0)))
                .andExpect(jsonPath("$.description", Matchers.is("Updated payment")));

        verify(paymentService).updatePayment(id, paymentDto);
    }

    @Test
    public void testDeletePayment() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/api/v1/payments/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(paymentService).deletePayment(id);
    }
}
