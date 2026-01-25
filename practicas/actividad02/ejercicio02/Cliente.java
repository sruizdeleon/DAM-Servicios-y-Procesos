package practicas.actividad02.ejercicio02;

import java.util.Random;

public class Cliente extends Thread {

    private final int MAX_COMPRAS_POR_CLIENTE = 10;
    private final int MAX_TIEMPO_SIN_COMPRA = 600;
    private final int MIN_TIEMPO_SIN_COMPRA = 300;
    private AlmacenCamisetas almacen;
    private String nombre;
    private int compras = 0;
    private Random random = new Random();

    public Cliente(String nombre, AlmacenCamisetas almacen){
        this.nombre = nombre;
        this.almacen = almacen;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        while (compras < MAX_COMPRAS_POR_CLIENTE) {
            try {
                // Calculo el rango de tiempo aleatorio
                int rango = MAX_TIEMPO_SIN_COMPRA - MIN_TIEMPO_SIN_COMPRA;
                // Creo random y le sumo el mínimo para que entre dentro del rango
                long tiempo = random.nextInt(rango) + MIN_TIEMPO_SIN_COMPRA;
                // Dormimos el hilo
                Thread.sleep(tiempo);
                // Comprobamos en el almacén si podemos comprar;
                almacen.comprarCamiseta(nombre);
                // Si hemos comprado aumentamos el número de compras
                compras++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Cliente "+nombre+" ha completado su máximo de compras ("+compras+").");
    }
}
