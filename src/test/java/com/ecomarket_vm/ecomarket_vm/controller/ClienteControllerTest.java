package com.ecomarket_vm.ecomarket_vm.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.ecomarket_vm.ecomarket_vm.model.Cliente;
import com.ecomarket_vm.ecomarket_vm.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1);
        cliente.setRun("12345678-9");
        cliente.setNombre("Juan");
        cliente.setApellido("Perez");
        cliente.setCorreo("juan.perez@gmail.com");
        cliente.setDireccionEnvio("Av. Jose Miguel Carrera 8000");
    }

    @Test
    public void testGetAllClientes() throws Exception {
        when(clienteService.findAll()).thenReturn(List.of(cliente));

        mockMvc.perform(get("/api/v1/cliente"))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].run").value("12345678-9"))
                .andExpect(jsonPath("$[0].nombre").value("Juan"))
                .andExpect(jsonPath("$[0].apellido").value("Perez"))
                .andExpect(jsonPath("$[0].correo").value("juan.perez@gmail.com"))
                .andExpect(jsonPath("$[0].direccionEnvio").value("Av. Jose Miguel Carrera 8000"));
    }

    @Test
    public void testGetClienteById() throws Exception {
        when(clienteService.findById(1)).thenReturn(cliente);

        mockMvc.perform(get("/api/v1/cliente/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.run").value("12345678-9"))
                .andExpect(jsonPath("$[0].nombre").value("Juan"))
                .andExpect(jsonPath("$[0].apellido").value("Perez"))
                .andExpect(jsonPath("$[0].correo").value("juan.perez@gmail.com"))
                .andExpect(jsonPath("$[0].direccionEnvio").value("Av. Jose Miguel Carrera 8000"));
    }

    @Test
    public void testCreateCliente() throws Exception {
        when(clienteService.save(any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(post("/api/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.run").value("12345678-9"))
                .andExpect(jsonPath("$[0].nombre").value("Juan"))
                .andExpect(jsonPath("$[0].apellido").value("Perez"))
                .andExpect(jsonPath("$[0].correo").value("juan.perez@gmail.com"))
                .andExpect(jsonPath("$[0].direccionEnvio").value("Av. Jose Miguel Carrera 8000"));
    }

    @Test
    public void testUpdateCliente() throws Exception {
        when(clienteService.save(any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(put("/api/v1/cliente/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.run").value("12345678-9"))
                .andExpect(jsonPath("$[0].nombre").value("Juan"))
                .andExpect(jsonPath("$[0].apellido").value("Perez"))
                .andExpect(jsonPath("$[0].correo").value("juan.perez@gmail.com"))
                .andExpect(jsonPath("$[0].direccionEnvio").value("Av. Jose Miguel Carrera 8000"));
    }

    @Test
    public void testDeleteCliente() throws Exception {
        
        doNothing().when(clienteService).deleteById(1);

        
        mockMvc.perform(delete("/api/v1/cliente/1"))
                .andExpect(status().isOk());

        
        verify(clienteService, times(1)).deleteById(1);
    }
    
}
