package com.ecomarket_vm.ecomarket_vm.service;

import com.ecomarket_vm.ecomarket_vm.model.Cliente;
import com.ecomarket_vm.ecomarket_vm.model.Envio;
import com.ecomarket_vm.ecomarket_vm.model.Producto;
import com.ecomarket_vm.ecomarket_vm.repository.EnvioRepository;
import com.ecomarket_vm.ecomarket_vm.service.EnvioService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
@ActiveProfiles("test")
public class EnvioServiceTest {
    
    @Autowired
    private EnvioService envioService;

    @MockBean
    private EnvioRepository envioRepository;

    @Test
    public void testFindAll() {
        Envio envio = crearEnvio();
        when(envioRepository.findAll()).thenReturn(List.of(envio));

        List<Envio> envios = envioService.findAll();
        assertNotNull(envios);
        assertEquals(1, envios.size());
        assertEquals(envio.getId(), envios.get(0).getId());
    }

    @Test
    public void testFindsById() {
        Integer id = 1;
        Envio envio = crearEnvio();
        when(envioRepository.findById(id)).thenReturn(Optional.of(envio));

        Envio found = envioService.findById(id);
        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testSave() {
        Envio envio = crearEnvio();
        when(envioRepository.save(envio)).thenReturn(envio);

        Envio saved = envioService.save(envio);
        assertNotNull(saved);
        assertEquals(1, saved.getEstado());
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;
        doNothing().when(envioRepository).deleteById(id);

        envioService.deleteById(id);
        verify(envioRepository, times(1)).deleteById(id);    
    }

    private Envio crearEnvio() {
        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setRun("12345678-9");
        cliente.setNombre("Juan");
        cliente.setApellido("Perez");
        cliente.setCorreo("juan.perez@gmail.com");
        cliente.setDireccionEnvio("Av. Jose Miguel Carrera 8000");

        Producto producto = new Producto();
        producto.setCodigo(1);
        producto.setNombre("Bolsa de maiz");
        producto.setDescripcion("Una bolsa de maiz de 20kg");
        producto.setPrecio(12000);
        producto.setCantidad(10);

        Envio envio = new Envio();
        envio.setId(1);
        envio.setCliente(cliente);
        envio.setProducto(producto);
        envio.setRunComprador(cliente.getRun());
        envio.setFechaCompra(new Date());
        envio.setFechaEntrega(new Date());
        envio.setEstado(1);

        return envio;
    }
}
