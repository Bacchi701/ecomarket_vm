package com.ecomarket_vm.ecomarket_vm.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ecomarket_vm.ecomarket_vm.model.Producto;
import com.ecomarket_vm.ecomarket_vm.repository.ProductoRepository;
import com.ecomarket_vm.ecomarket_vm.service.ProductoService;

@SpringBootTest
public class ProductoServiceTest {
    
    @Autowired
    private ProductoService productoService;
    @MockBean
    private ProductoRepository productoRepository;

    @Test
    public void testFindAll() {
        when(productoRepository.findAll()).thenReturn(List.of(new Producto(1, "Bolsa de maiz", "Una bolsa de maiz de 20kg", 12000, 10)));

        List<Producto> productos = productoService.findAll();
        assertNotNull(productos);
        assertEquals(1, productos.size());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Producto producto = new Producto(id,"Bolsa de maiz", "Una bolsa de maiz de 20kg", 12000, 10);
        when(productoRepository.findById(id)).thenReturn(Optional.of(producto));

        Producto found = productoService.findById(id);
        assertNotNull(found);
        assertEquals(id, found.getCodigo());
    }

    @Test
    public void testSave() {
        Producto producto = new Producto(1, "Bolsa de maiz", "Una bolsa de maiz de 20kg", 12000, 10);
        when(productoRepository.save(producto)).thenReturn(producto);

        Producto saved = productoService.save(producto);
        assertNotNull(saved);
        assertEquals("Bolsa de maiz", saved.getNombre());
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;
        doNothing().when(productoRepository).deleteById(id);

        productoService.deleteById(id);
        verify(productoRepository, times(1)).deleteById(id);
    }
}
