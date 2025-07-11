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
//import com.ecomarket_vm.ecomarket_vm.service.ProductoService;

@SpringBootTest
public class ProductoServiceTest {
    
    // Inyecta el servicio de Producto para ser probado.
    @Autowired
    private ProductoService productoService;

    // Crea un mock del repositorio de Carrera para simular su comportamiento.
    @MockBean
    private ProductoRepository productoRepository;

    @Test
    public void testFindAll() {
        // Define el comportamiento del mock: cuando se llame a findAll(), devuelve una lista con un Producto.
        when(productoRepository.findAll()).thenReturn(List.of(new Producto(1, "Bolsa de maiz", "Una bolsa de maiz de 20kg", 12000, 10)));

        // Llama al método findAll() del servicio.
        List<Producto> productos = productoService.findAll();

        // Verifica que la lista devuelta no sea nula y contenga exactamente un Producto.
        assertNotNull(productos);
        assertEquals(1, productos.size());
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Producto producto = new Producto(id,"Bolsa de maiz", "Una bolsa de maiz de 20kg", 12000, 10);

        // Define el comportamiento del mock: cuando se llame a findById() con "1", devuelve un Producto opcional.
        when(productoRepository.findById(id)).thenReturn(Optional.of(producto));

        // Llama al método findByCodigo() del servicio.
        Producto found = productoService.findById(id);

        // Verifica que el Producto devuelta no sea nula y que su código coincida con el código esperado
        assertNotNull(found);
        assertEquals(id, found.getCodigo());
    }

    @Test
    public void testSave() {
        Producto producto = new Producto(1, "Bolsa de maiz", "Una bolsa de maiz de 20kg", 12000, 10);

        // Define el comportamiento del mock: cuando se llame a save(), devuelve el Producto proporcionado.
        when(productoRepository.save(producto)).thenReturn(producto);

        // Llama al método save() del servicio.
        Producto saved = productoService.save(producto);

        // Verifica que el Producto guardado no sea nulo y que su nombre coincida con el nombre esperado.
        assertNotNull(saved);
        assertEquals("Bolsa de maiz", saved.getNombre());
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;

        // Define el comportamiento del mock: cuando se llame a deleteById(), no hace nada.
        doNothing().when(productoRepository).deleteById(id);

        // Llama al método deleteByCodigo() del servicio.
        productoService.deleteById(id);

        // Verifica que el método deleteById() del repositorio se haya llamado exactamente una vez con el código proporcionado.
        verify(productoRepository, times(1)).deleteById(id);
    }
}
