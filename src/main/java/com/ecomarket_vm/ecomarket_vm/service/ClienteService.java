package com.ecomarket_vm.ecomarket_vm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomarket_vm.ecomarket_vm.model.Cliente;
import com.ecomarket_vm.ecomarket_vm.repository.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente findById(long id){
        return clienteRepository.findById(id).get();
    }

    public Cliente save(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public void delete(Long id){
        clienteRepository.deleteById(id);
    }
}
