package practicas.activida02.ejercicio01;

import java.util.Random;

public class Camarero extends Thread {
    private final int MAX_COMANDAS_ATENDIDAS = 10;
    private final int MAX_TIEMPO_SIN_ATENDER = 400;
    private final int MIN_TIEMPO_SIN_ATENDER = 200;
    private final Estanteria estanteria;
    private final String nombre;
    private final Random random = new Random();
    private int comandasAtendidas = 1;

    public Camarero(String nombre, Estanteria estanteria) {
        this.nombre = nombre;
        this.estanteria = estanteria;
    }

    @Override
    public void run() {
        while (comandasAtendidas < MAX_COMANDAS_ATENDIDAS) {
            try {
                // Calculo el rango de tiempo aleatorio
                int rango = MAX_TIEMPO_SIN_ATENDER - MIN_TIEMPO_SIN_ATENDER;
                // Creo random y le sumo el mínimo para que entre dentro del rango
                long tiempo = random.nextInt(rango) + MIN_TIEMPO_SIN_ATENDER;
                // Dormimos el hilo
                Thread.sleep(tiempo);
                // Creamos la comanda
                String comanda = "Comanda "+comandasAtendidas;
                // Comprobamos en la estanteria podemos dejar comandas;
                estanteria.dejarComanda(nombre, comanda);
                // Si hemos creado la comanda aumentamos el número de comandas
                comandasAtendidas++;
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println("Camarero " + nombre + " ha terminado de atender sus mesas ("+comandasAtendidas+"). Se va a casa.");
    }
}