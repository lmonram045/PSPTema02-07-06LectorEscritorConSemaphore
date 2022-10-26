package Principal;

import java.util.concurrent.Semaphore;

/** Clase que implementa al hilo lector */
public class Lector extends Thread {
    private Semaphore semaforo; // Semaforo que controla el acceso

    /**
     * Constructor de la clase Lector
     * @param nombre Nombre del hilo
     * @param semaforo Objeto Semaphore para controlar el acceso
     */
    public Lector(String nombre, Semaphore semaforo) {
        super(nombre); // Nombre del hilo
        this.semaforo = semaforo;
    }

    /**
     * Sobreescritura del método run(), el semáforo adquiere 1 permiso, duerme el hilo un tiempo aleatorio, y
     * libera ese permiso
     */
    @Override
    public void run() {
        System.out.println("El hilo " + getName() + " quiere leer"); // Mensaje para comprobar funcionamiento

        try {
            // Adquiere 1 permiso para asegurarse de que la BD esté vacía
            semaforo.acquire();
        } catch (InterruptedException ex) {
            System.err.println("Error en el hilo " + getName() + ": " + ex.getMessage());
            ex.printStackTrace();
        }
        System.out.println("El hilo " + getName() + " está leyendo"); // Mensaje para comprobar funcionamiento
        try {
            // Se duerme al hilo un tiempo aleatorio
            sleep((long) (Math.random() * 1000)); // Para simular que tarda en realizar su tarea y así otros hilos compiten por el acceso a la BD
        } catch (InterruptedException ex) {
            System.err.println("Error en el hilo " + getName() + ": " + ex.getMessage());
            ex.printStackTrace();
        }
        semaforo.release(); // Libera el permiso adquirido para indicar que la BD está vacía
        System.out.println("El hilo " + getName() + " ha terminado de leer"); // Mensaje para comprobar funcionamiento
    }
}
