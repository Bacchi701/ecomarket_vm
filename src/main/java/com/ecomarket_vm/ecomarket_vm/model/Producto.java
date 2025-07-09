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

public class Producto { // idProducto, nombre, descripcion, precio, cantidad

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;
    
    @Column(unique=true, length = 13, nullable=false)
    private String nombre;

    @Column(nullable=false)
    private String descripcion;

    @Column(nullable=false)
    private Integer precio;

    @Column(nullable=false)
    private Integer cantidad;
    
    
}
