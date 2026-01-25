package practicas.actividad02.ejercicio01;

import java.util.Random;

public class Cocinero extends Thread {
    private final int MAX_COMANDAS_COCINADAS = 10;
    private final int MAX_TIEMPO_SIN_COCINAR = 350;
    private final int MIN_TIEMPO_SIN_COCINAR = 250;
    private final Estanteria estanteria;
    private final String nombre;
    private final Random random = new Random();
    private int comidasCocinadas = 1;

    public Cocinero(String nombre, Estanteria estanteria) {
        this.nombre = nombre;
        this.estanteria = estanteria;
    }

    @Override
    public void run() {
        while (comidasCocinadas < MAX_COMANDAS_COCINADAS) {
            try {
                // Calculo el rango de tiempo aleatorio
                int rango = MAX_TIEMPO_SIN_COCINAR - MIN_TIEMPO_SIN_COCINAR;
                // Creo random y le sumo el mínimo para que entre dentro del rango
                long tiempo = random.nextInt(rango) + MIN_TIEMPO_SIN_COCINAR;
                // Dormimos el hilo
                Thread.sleep(tiempo);
                // Comprobamos en la estanteria si hay comandas;
                estanteria.recogerComanda(nombre);
                // Si hay comandas aumentamos el número de platos cocinados
                comidasCocinadas++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Cocinero " + nombre + " ha terminado de cocinar sus comidas ("+comidasCocinadas+"). Se va a casa.");
    }
}