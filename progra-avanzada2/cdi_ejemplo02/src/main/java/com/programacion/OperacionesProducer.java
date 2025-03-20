package com.programacion;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.util.AnnotationLiteral;
import jakarta.inject.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ApplicationScoped
public class OperacionesProducer {

    @Produces
    @MiBean
    public Operaciones create() {
        return new OperacionesImpl();
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.FIELD})
    public @interface MiBean {

        public static final class Literal extends AnnotationLiteral<MiBean> implements MiBean {
            private static final long serialVersionUID = 1L;

            public static final Literal INSTANCE = new Literal();
        }

    }

}
