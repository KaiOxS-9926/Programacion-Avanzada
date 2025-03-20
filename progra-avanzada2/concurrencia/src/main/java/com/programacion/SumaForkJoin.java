package com.programacion;

import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

class RecursiveTask extends java.util.concurrent.RecursiveTask<Integer> {

    public static final Integer TAMANIO_MINIMO = 10_000_000;

    private int[] datos;
    private int inicio;
    private int fin;

    public RecursiveTask(int[] datos, int b, int fin) {
        this.datos = datos;
        this.inicio = b;
        this.fin = fin;
    }

    public int sumaSerial() {
        int suma = 0;
        for (int i = inicio; i < fin; i++) {
            var tmp = Math.log(datos[i]);
            var tmp2 = Math.sin(tmp);
            suma += datos[i];
        }
        return suma;
    }

    @Override
    protected Integer compute() {
        //System.out.println(Thread.currentThread().getName() + " " + inicio + " " + fin);

        int tamanio = fin - inicio;

        if (tamanio < TAMANIO_MINIMO) {
            //suma serial
            return sumaSerial();
        } else {
            //dividir en dos tareas

            RecursiveTask izq = new RecursiveTask(datos, inicio, inicio + tamanio / 2);
            RecursiveTask der = new RecursiveTask(datos, inicio + tamanio / 2, fin);

            // El orden es importante de lo contrario es serial
            izq.fork();
            //der.fork();

            int sumaDerecha = der.compute();
            int sumaIzquierda = izq.join();

            return sumaIzquierda + sumaDerecha;
        }
    }
}

public class SumaForkJoin {

    public static void main(String[] args) {

        int total = 100_000_000;
        int[] valores = IntStream.rangeClosed(1, total).toArray();

        System.out.println("Procesadores: " + Runtime.getRuntime().availableProcessors());

        RecursiveTask task = new RecursiveTask(valores, 0, valores.length);
        ForkJoinPool pool = new ForkJoinPool();

        /** tarea 0: 0            ...50_000_000
         *  tarea 1: 50_000_000   ...100_000_000
         *
         *  tarea 0: 0            ...25_000_000
         *  tarea 1: 25_000_000   ...50_000_000
         *  tarea 2: 50_000_000   ...75_000_000
         *  tarea 3: 75_000_000   ...100_000_000
         */

        // --suma concurrente

        Integer suma1 = 0;
        long start = System.currentTimeMillis();
        {
            suma1 = pool.invoke(task);
        }
        long end = System.currentTimeMillis();
        System.out.printf("Suma paralela: %d, tiempo:%d\n", suma1, (end - start));

        // --suma serial

        Integer suma2;
        start = System.currentTimeMillis();
        {
            suma2 = task.sumaSerial();
        }
        end = System.currentTimeMillis();
        System.out.printf("Suma serial: %d, tiempo:%d\n", suma2, (end - start));

        //Dado un número grande n contar cuantos números primos hay


    }
}