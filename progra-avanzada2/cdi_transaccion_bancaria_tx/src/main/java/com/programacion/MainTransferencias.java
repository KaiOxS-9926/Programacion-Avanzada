package com.programacion;

import com.programacion.servicios.interfaces.ManejadorPersistencia;
import com.programacion.servicios.interfaces.TransferenciaBancaria;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class MainTransferencias {
    public static void main(String[] args) {
        SeContainer container = SeContainerInitializer.newInstance()
                .initialize();

        TransferenciaBancaria servicio = container.select(TransferenciaBancaria.class).get();

        System.out.println(servicio);

        String num1 = "001";
        String num2 = "009";


        var manejadorPersistencia = container.select(ManejadorPersistencia.class).get();

        //var cuenta1 = manejadorPersistencia.buscarCuenta("001");
        //var cuenta2 = manejadorPersistencia.buscarCuenta("009");

        //System.out.println(cuenta1);
        //System.out.println(cuenta2);

        servicio.transferir(num1, num2, 50);

        //cuenta1 = manejadorPersistencia.buscarCuenta("001");
        //cuenta2 = manejadorPersistencia.buscarCuenta("009");

        //System.out.println(cuenta1);
        //System.out.println(cuenta2);

    }
}
