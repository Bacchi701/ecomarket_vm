package com.ecomarket_vm.ecomarket_vm;

import com.ecomarket_vm.ecomarket_vm.model.*;
import com.ecomarket_vm.ecomarket_vm.repository.*;

import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private EnvioRepository envioRepository;
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        // Generar clientes
        for (int i = 0; i < 50; i++) {
            Cliente cliente = new Cliente();
            cliente.setId(i + 1);
            cliente.setRun(faker.idNumber().valid());
            cliente.setNombre(faker.name().firstName());
            cliente.setApellido(faker.name().lastName());
            cliente.setCorreo(faker.internet().emailAddress());
            cliente.setDireccionEnvio(faker.address().fullAddress());
            clienteRepository.save(cliente);
        }
        
        List<Cliente> clientes = clienteRepository.findAll();
        // Generar cuentas
        for (int i = 0; i < 50; i++) {
            Cuenta cuenta = new Cuenta();
            cuenta.setId(i + 1);
            cuenta.setUsuario(faker.internet().username());
            cuenta.setPassword(faker.internet().password());
            cuenta.setRol(faker.basketball().positions());
            cuenta.setCliente(clientes.get(random.nextInt(clientes.size())));
            cuentaRepository.save(cuenta);
        }

        List<Producto> productos = productoRepository.findAll();
        // Generar envios
        for (int i = 0; i < 50; i++) {
            Envio envio = new Envio();
            envio.setId(i + 1);
            envio.setCliente(clientes.get(random.nextInt(clientes.size())));
            envio.setRunComprador(faker.idNumber().valid());
            envio.setFechaCompra(new Date());
            envio.setFechaEntrega(new Date());
            envio.setProducto(productos.get(random.nextInt(productos.size())));
            envioRepository.save(envio);
        }

        
        // Generar productos
        for (int i = 0; i < 20; i++) {
            Producto producto = new Producto();
            producto.setCodigo(i + 1);
            producto.setNombre(faker.brand().car());
            producto.setDescripcion(faker.text().text(5, 20));
            producto.setPrecio(faker.number().numberBetween(10000, 99999));
            producto.setCantidad(faker.number().randomDigit());
            productoRepository.save(producto);
        }
    }
}