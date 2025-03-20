package com.programacion.servicios.impl;

import com.programacion.servicios.interfaces.ManejadorPersistencia;
import com.programacion.servicios.interfaces.TransferenciaBancaria;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class TransferenciaBancariaImpl implements TransferenciaBancaria {

    @Inject
    ManejadorPersistencia manejadorPersistencia;

    @Override
    public void transferir(String num1, String num2, double monto) {

        // Crédito a la cuenta1
        var cuenta1 = manejadorPersistencia.buscarCuenta(num1);
        manejadorPersistencia.actualizarSaldo(cuenta1, monto);

        // Débito a la cuenta2
        var cuenta2 = manejadorPersistencia.buscarCuenta(num2);
        manejadorPersistencia.actualizarSaldo(cuenta2, -monto);
    }
}
