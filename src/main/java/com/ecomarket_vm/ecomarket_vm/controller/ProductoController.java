package com.ecomarket_vm.ecomarket_vm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomarket_vm.ecomarket_vm.model.Producto;
import com.ecomarket_vm.ecomarket_vm.service.ProductoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoService.findAll();
    }
    
    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productoService.save(producto);
    }

    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable Integer id) {
        return productoService.findById(id);
    }


    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Integer id, @RequestBody Producto producto) {
        producto.setCodigo(id);
        producto.setNombre(producto.getNombre());
        producto.setDescripcion(producto.getDescripcion());
        producto.setPrecio(producto.getPrecio());
        producto.setCantidad(producto.getCantidad());
        return productoService.save(producto);
    }

    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable Integer id) {
        productoService.deleteById(id);
    }
}