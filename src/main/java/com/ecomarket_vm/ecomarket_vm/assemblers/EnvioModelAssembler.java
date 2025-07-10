package com.ecomarket_vm.ecomarket_vm.assemblers;

import com.ecomarket_vm.ecomarket_vm.controller.EnvioControllerV2;
import com.ecomarket_vm.ecomarket_vm.model.Envio;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EnvioModelAssembler implements RepresentationModelAssembler<Envio, EntityModel<Envio>> {
    
    @Override
    public EntityModel<Envio> toModel(Envio envio) {
        return EntityModel.of(envio,
                linkTo(methodOn(EnvioControllerV2.class).getEnvioById(envio.getId())).withSelfRel(),
                linkTo(methodOn(EnvioControllerV2.class).getAllEnvios()).withRel("envios"));
    }
}
