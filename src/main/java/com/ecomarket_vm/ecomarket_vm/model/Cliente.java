package com.ecomarket_vm.ecomarket_vm.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Cliente { // id, run, nombre, apellido, correo, direccionEnvio

    @Id
    private Integer id;
    private String run;
    private String nombre;
    private String apellido;
    private String correo;
    private String direccionEnvio;

}