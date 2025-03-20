package com.programacion.servicios.impl;

import com.programacion.servicios.interfaces.ManejadorPersistencia;
import com.programacion.servicios.interfaces.TransferenciaBancaria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransferenciaBancariaImpl implements TransferenciaBancaria {

    @Autowired
    ManejadorPersistencia manejadorPersistencia;

    @Override
    public void transferir(String num1, String num2, double monto) {
        var cuenta1 = manejadorPersistencia.buscarCuenta(num1);
        var cuenta2 = manejadorPersistencia.buscarCuenta(num2);

        // Crédito a la cuenta1
        manejadorPersistencia.actualizarSaldo(cuenta1, monto);

        // Débito a la cuenta2
        manejadorPersistencia.actualizarSaldo(cuenta2, -monto);
    }
}
