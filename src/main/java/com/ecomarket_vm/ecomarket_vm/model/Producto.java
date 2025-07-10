package com.ecomarket_vm.ecomarket_vm.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Producto { // codigo, nombre, descripcion, precio, cantidad

    @Id
    private Integer codigo;
    private String nombre;
    private String descripcion;
    private Integer precio;
    private Integer cantidad;
    
    @ManyToOne
    @JoinColumn(name = "id_envio", nullable = false)
    private Envio envio;

}
