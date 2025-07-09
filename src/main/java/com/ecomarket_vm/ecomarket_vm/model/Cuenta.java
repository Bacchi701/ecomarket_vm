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

    @OneToOne
    @JoinColumn(name = "id_envio", nullable = false)
    private Envio envio;
}
