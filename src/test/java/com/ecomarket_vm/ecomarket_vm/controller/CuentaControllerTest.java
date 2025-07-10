package com.ecomarket_vm.ecomarket_vm.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.ecomarket_vm.ecomarket_vm.model.Cuenta;
import com.ecomarket_vm.ecomarket_vm.service.CuentaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(CuentaController.class)
public class CuentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CuentaService cuentaService;

    @Autowired
    private ObjectMapper objectMapper;

    private Cuenta cuenta;

    @BeforeEach
    void setUp() {
        cuenta = new Cuenta();
        cuenta.setId(1);
        cuenta.setUsuario("Juancito01");
        cuenta.setPassword("asd123");
        cuenta.setRol("Cliente");
    }

    @Test
    public void testGetAllCuentas() throws Exception {
        when(cuentaService.findAll()).thenReturn(List.of(cuenta));

        mockMvc.perform(get("/api/cuentas"))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].usuario").value("Juancito01"))
                .andExpect(jsonPath("$[0].password").value("asd123"))
                .andExpect(jsonPath("$[0].rol").value("Cliente"));
    }

    @Test
    public void testGetCuentaById() throws Exception {
        when(cuentaService.findById(1)).thenReturn(cuenta);

        mockMvc.perform(get("/api/cuentas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$[0].usuario").value("Juancito01"))
                .andExpect(jsonPath("$[0].password").value("asd123"))
                .andExpect(jsonPath("$[0].rol").value("Cliente"));
    }

    @Test
    public void testCreateCuenta() throws Exception {
        when(cuentaService.save(any(Cuenta.class))).thenReturn(cuenta);

        mockMvc.perform(post("/api/cuentas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cuenta)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$[0].usuario").value("Juancito01"))
                .andExpect(jsonPath("$[0].password").value("asd123"))
                .andExpect(jsonPath("$[0].rol").value("Cliente"));
    }

    @Test
    public void testUpdateCuenta() throws Exception {
        when(cuentaService.save(any(Cuenta.class))).thenReturn(cuenta);

        mockMvc.perform(put("/api/cuentas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cuenta)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$[0].usuario").value("Juancito01"))
                .andExpect(jsonPath("$[0].password").value("asd123"))
                .andExpect(jsonPath("$[0].rol").value("Cliente"));
    }

    @Test
    public void testDeleteCuenta() throws Exception {
        
        doNothing().when(cuentaService).deleteById(1);

        
        mockMvc.perform(delete("/api/cuentas/1"))
                .andExpect(status().isOk());

        
        verify(cuentaService, times(1)).deleteById(1);
    }

    
}
