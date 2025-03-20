package com.programacion;

import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class CdiEjemplo01Main {

    public static void main(String[] args) {
        try (SeContainer container = SeContainerInitializer
                .newInstance()
                .initialize()) {

            //Buscar la dependencia

            Instance<Operaciones> obj = container.select(Operaciones.class);
            Operaciones servicio = obj.get();

            int resultado = servicio.sumar(25, 35);
            System.out.println("Resultado: " + resultado);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
