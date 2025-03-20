package com.programacion;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OperacionesImpl implements Operaciones {

    @Override
    public int sumar(int a, int b) {
        return a + b;
    }
}
