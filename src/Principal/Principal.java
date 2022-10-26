package Principal;

import java.util.concurrent.Semaphore;

public class Principal {
    public static void main(String[] args) {
        Semaphore semaforo = new Semaphore(5); // Semáforo con 5 permisos (permite el acceso a 5 hilos)

        // Se crean e inician 2 hilos escritor
        for (int i = 0; i < 2; i++) {
            Escritor escritor = new Escritor("Escritor " + i, semaforo);
            escritor.start();
        }

        // Se crean e inician 5 hilos lector
        for (int i = 0; i < 5; i++) {
            Lector lector = new Lector("Lector " + i, semaforo);
            lector.start();
        }
    }
}
