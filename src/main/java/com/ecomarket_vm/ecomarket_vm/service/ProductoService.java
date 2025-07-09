package com.ecomarket_vm.ecomarket_vm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomarket_vm.ecomarket_vm.model.Producto;
import com.ecomarket_vm.ecomarket_vm.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll(){
        return productoRepository.findAll();
    }
    
    public Producto findById(long id){
        return productoRepository.findById(id).get();
    }
    
    public Producto save(Producto producto){
        return productoRepository.save(producto);
    }

    public void delete(Long id){
        productoRepository.deleteById(id);
    }
}