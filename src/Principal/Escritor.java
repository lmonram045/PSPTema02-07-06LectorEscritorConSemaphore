package Principal;

import java.util.concurrent.Semaphore;

/** Clase que implementa al hilo escritor */
public class Escritor extends Thread {
    private Semaphore semaforo; // Semaforo que controla el acceso

    /**
     * Constructor de la clase Escritor
     * @param nombre Nombre del hilo
     * @param semaforo Objeto Semaphore para controlar el acceso
     */
    public Escritor(String nombre, Semaphore semaforo) {
        super(nombre); // Nombre del hilo
        this.semaforo = semaforo;
    }

    /**
     * Sobreescritura del método run(), el semáforo adquiere 5 permisos, duerme el hilo un tiempo aleatorio, y
     * libera esos permisos
     */
    @Override
    public void run() {
        System.out.println("El hilo " + getName() + " quiere escribir"); // Mensaje para comprobar funcionamiento

        try {
            // Adquiere 5 permisos para asegurarse de que la BD esté vacía
            semaforo.acquire(5);
        } catch (InterruptedException ex) {
            System.err.println("Error en el hilo " + getName() + ": " + ex.getMessage());
            ex.printStackTrace();
        }
        System.out.println("El hilo " + getName() + " está escribiendo"); // Mensaje para comprobar funcionamiento
        try {
            // Se duerme al hilo un tiempo aleatorio (para simular que tarda en realizar su tarea y así otros hilos
            // compiten por el acceso a la BD)
            sleep((long) (Math.random() * 1000));
        } catch (InterruptedException ex) {
            System.err.println("Error en el hilo " + getName() + ": " + ex.getMessage());
            ex.printStackTrace();
        }

        semaforo.release(5); // Libera los 5 permisos adquiridos para indicar que la BD está vacía
        System.out.println("El hilo " + getName() + " ha terminado de escribir"); // Mensaje para comprobar funcionamiento
    }
}
