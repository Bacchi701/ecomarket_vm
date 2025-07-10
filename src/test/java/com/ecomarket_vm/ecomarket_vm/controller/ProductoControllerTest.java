package com.ecomarket_vm.ecomarket_vm.controller;



import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.ecomarket_vm.ecomarket_vm.model.Producto;
import com.ecomarket_vm.ecomarket_vm.service.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto();
        producto.setCodigo(1);
        producto.setNombre("Bolsa de maiz");
        producto.setDescripcion("Una bolsa de maiz de 20kg");
        producto.setPrecio(12000);
        producto.setCantidad(10);
    }

    @Test
    public void testGetAllProductos() throws Exception {
        when(productoService.findAll()).thenReturn(List.of(producto));

        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].codigo").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Bolsa de maiz"))
                .andExpect(jsonPath("$[0].descripcion").value("Una bolsa de maiz de 20kg"))
                .andExpect(jsonPath("$[0].precio").value(12000))
                .andExpect(jsonPath("$[0].cantidad").value(10));
    }

    @Test
    public void testGetProductoById() throws Exception {
        when(productoService.findById(1)).thenReturn(producto);

        mockMvc.perform(get("/api/productos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo").value(1))
                .andExpect(jsonPath("$.nombre").value("Bolsa de maiz"))
                .andExpect(jsonPath("$.descripcion").value("Una bolsa de maiz de 20kg"))
                .andExpect(jsonPath("$.precio").value(12000))
                .andExpect(jsonPath("$.cantidad").value(10));
    }

    @Test
    public void testCreateProducto() throws Exception {
        when(productoService.save(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo").value(1))
                .andExpect(jsonPath("$.nombre").value("Bolsa de maiz"))
                .andExpect(jsonPath("$.descripcion").value("Una bolsa de maiz de 20kg"))
                .andExpect(jsonPath("$.precio").value(12000))
                .andExpect(jsonPath("$.cantidad").value(10));
    }

    @Test
    public void testUpdateProducto() throws Exception {
        when(productoService.save(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(put("/api/productos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo").value(1))
                .andExpect(jsonPath("$.nombre").value("Bolsa de maiz"))
                .andExpect(jsonPath("$.descripcion").value("Una bolsa de maiz de 20kg"))
                .andExpect(jsonPath("$.precio").value(12000))
                .andExpect(jsonPath("$.cantidad").value(10));
    }

    @Test
    public void testDeleteProducto() throws Exception {
        doNothing().when(productoService).deleteById(1);

        mockMvc.perform(delete("/api/productos/1"))
                .andExpect(status().isOk());

        verify(productoService, times(1)).deleteById(1);
    }
}
