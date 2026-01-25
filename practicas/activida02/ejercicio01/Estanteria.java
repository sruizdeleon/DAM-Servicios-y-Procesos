package practicas.activida02.ejercicio01;

import java.util.LinkedList;
import java.util.Queue;

public class Estanteria {
    private final int CAPACIDAD_MAXIMA = 10;
    private final Queue<String> colaComandas = new LinkedList<>();

    public synchronized void dejarComanda(String nombreCamarero, String comanda) {
        // Si está llena, el camarero espera
        while (colaComandas.size() >= CAPACIDAD_MAXIMA) {
            try {
                System.out.println("(!) Estantería llena. " + nombreCamarero + " esperando...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        colaComandas.add(comanda);
        System.out.println(nombreCamarero + " deja comanda (" + comanda + ") -> Total en estantería: " + colaComandas.size());

        // Notifica a los cocineros que hay algo que recoger
        notifyAll();
    }

    public synchronized void recogerComanda(String nombreCocinero) {
        // El cocinero espera si la cola está vacía Y los camareros siguen trabajando
        while (colaComandas.isEmpty()) {
            try {
                System.out.println("... " + nombreCocinero + " esperando comandas");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Si la cola está vacía
        String comanda = colaComandas.poll();
        System.out.println(nombreCocinero + " recoge comanda (" + comanda + ") -> Total en estantería: " + colaComandas.size());

        // Notifica a los camareros que hay espacio
        notifyAll();
    }

}