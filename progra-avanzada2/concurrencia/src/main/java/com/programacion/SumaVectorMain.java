package com.programacion;

import java.util.concurrent.Callable;

public class SumaVectorMain {

    static class ResultHolder {
        Integer suma1, suma2, suma3, suma4, suma5, suma6, suma7, suma8 = 0;
    }

    static Integer sumaSerial(int[] datos) {
        int suma = 0;
        for (int i = 0; i < datos.length; i++) {
            suma = suma + datos[i];
        }
        return suma;
    }

    static class SumaTask implements Callable<Integer> {
        private int[] data;
        private int start;
        private int end;

        SumaTask(int[] data, int start, int end) {
            this.data = data;
            this.start = start;
            this.end = end;
        }

        @Override
        public Integer call() throws Exception {

            System.out.println(Thread.currentThread().getName() + " Calculando suma, start: " + start + ", end: " + end);

            try {
                Thread.sleep(5_000);
            } catch (Exception e) {

            }

            int suma = 0;
            for (int i = start; i < end; i++) {
                suma = suma + data[i];
            }

            System.out.println(Thread.currentThread().getName() + " Fin calculando suma, start: " + start + ", end: " + end);
            return suma;
        }
    }

    static Integer sumaThreads(int[] datos) throws Exception {
        int suma = 0;
        int octavo = datos.length / 8;

        SumaTask task1 = new SumaTask(datos, 0, octavo);
        SumaTask task2 = new SumaTask(datos, octavo, octavo * 2);
        SumaTask task3 = new SumaTask(datos, octavo * 2, octavo * 3);
        SumaTask task4 = new SumaTask(datos, octavo * 3, octavo * 4);
        SumaTask task5 = new SumaTask(datos, octavo * 4, octavo * 5);
        SumaTask task6 = new SumaTask(datos, octavo * 5, octavo * 6);
        SumaTask task7 = new SumaTask(datos, octavo * 6, octavo * 7);
        SumaTask task8 = new SumaTask(datos, octavo * 7, datos.length);

        /*
        int suma1 = task1.call();
        int suma2 = task2.call();

        suma = suma1 + suma2;
        */

        final ResultHolder result = new ResultHolder();

        Thread t0 = new Thread(() -> {
            try {
                result.suma1 = task1.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Thread t1 = new Thread(() -> {
            try {
                result.suma2 = task2.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                result.suma3 = task3.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                result.suma4 = task4.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Thread t4 = new Thread(() -> {
            try {
                result.suma5 = task5.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Thread t5 = new Thread(() -> {
            try {
                result.suma6 = task6.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Thread t6 = new Thread(() -> {
            try {
                result.suma7 = task7.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Thread t7 = new Thread(() -> {
            try {
                result.suma8 = task8.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();

        t0.join();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        t6.join();
        t7.join();

        return result.suma1 + result.suma2 + result.suma3 + result.suma4 + result.suma5 + result.suma6 + result.suma7 + result.suma8;
    }

    public static void main(String[] args) throws Exception {

        int[] vector = new int[1_000];

        for (int i = 0; i < vector.length; i++) {
            vector[i] = i;
        }

        int suma = sumaThreads(vector);
        System.out.println("\nSuma total: " + suma);



    }
}
