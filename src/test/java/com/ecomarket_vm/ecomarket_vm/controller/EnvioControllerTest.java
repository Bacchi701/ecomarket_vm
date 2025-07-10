package com.ecomarket_vm.ecomarket_vm.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.ecomarket_vm.ecomarket_vm.model.Envio;
import com.ecomarket_vm.ecomarket_vm.service.EnvioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

@WebMvcTest(EnvioController.class)
public class EnvioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnvioService envioService;

    @Autowired
    private ObjectMapper objectMapper;

    private Envio envio;

    @BeforeEach
    void setUp() {
        envio = new Envio();
        envio.setId(1);
        envio.setFechaCompra(new Date());
        envio.setFechaEntrega(new Date());
        envio.setRunComprador("12345678-9");
        envio.setEstado(1);
    }

    @Test
    public void testGetAllEnvios() throws Exception {
        when(envioService.findAll()).thenReturn(List.of(envio));

        mockMvc.perform(get("/api/envios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].runComprador").value("12345678-9"))
                .andExpect(jsonPath("$[0].estado").value(1));
    }

    @Test
    public void testGetEnvioById() throws Exception {
        when(envioService.findById(1)).thenReturn(envio);

        mockMvc.perform(get("/api/envios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.estado").value(1));
    }

    @Test
    public void testCreateEnvio() throws Exception {
        when(envioService.save(any(Envio.class))).thenReturn(envio);

        mockMvc.perform(post("/api/envios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(envio)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.estado").value(1));
    }

    @Test
    public void testUpdateEnvio() throws Exception {
        when(envioService.save(any(Envio.class))).thenReturn(envio);

        mockMvc.perform(put("/api/envios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(envio)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.estado").value(1));
    }

    @Test
    public void testDeleteEnvio() throws Exception {
        doNothing().when(envioService).deleteById(1);

        mockMvc.perform(delete("/api/envios/1"))
                .andExpect(status().isOk());

        verify(envioService, times(1)).deleteById(1);
    }
    
    
}
