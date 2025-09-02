package com.nttdata.product.management.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BaseDocument { //Solo es un helper para devolver el nombre de la clase en el campo _class
    private String _class;

    public String get_class() {
        return this.getClass().getSimpleName();
    }
}
