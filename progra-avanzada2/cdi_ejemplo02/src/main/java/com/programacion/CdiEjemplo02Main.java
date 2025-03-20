package com.programacion;

import jakarta.enterprise.inject.spi.Bean;
import jakarta.enterprise.inject.spi.BeanManager;
import org.apache.webbeans.config.WebBeansContext;
import org.apache.webbeans.spi.ContainerLifecycle;

public class CdiEjemplo02Main {

    public static void main(String[] args) {

        ContainerLifecycle lifecycle = WebBeansContext.getInstance()
                .getService(ContainerLifecycle.class);

        lifecycle.startApplication(null);

        BeanManager beanManager = lifecycle.getBeanManager();

        Bean<?> bean = beanManager.getBeans(Operaciones.class, OperacionesProducer.MiBean.Literal.INSTANCE)
                .iterator().next();

        var context = beanManager.createCreationalContext(bean);

        Operaciones servicio = (Operaciones) beanManager.getReference(bean, Operaciones.class, null);

        int ret = servicio.sumar(7, 10);
        System.out.println("Respuesta: " + ret);
    }
}



