package com.ecomarket_vm.ecomarket_vm.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cuenta")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Cuenta { // id, usuario, password, rol

    @Id
    private Integer id;
    private String usuario;
    private String password;
    private String rol;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
}
