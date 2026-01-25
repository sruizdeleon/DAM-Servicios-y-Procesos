package practicas.activida02.ejercicio02;

import java.util.Random;

public class Costurero extends Thread {

    private final int MAX_CONFECCIONES_POR_COSTURERO = 10;
    private final int MAX_TIEMPO_SIN_COSER = 400;
    private final int MIN_TIEMPO_SIN_COSER = 200;
    private AlmacenCamisetas almacenCamisetas;
    private AlmacenTelas almacenTelas;
    private String nombre;
    private int confecciones = 0;
    private Random random = new Random();

    public Costurero(String nombre, AlmacenTelas almacenTelas, AlmacenCamisetas almacenCamisetas){
        this.nombre = nombre;
        this.almacenTelas = almacenTelas;
        this.almacenCamisetas = almacenCamisetas;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        while (confecciones < MAX_CONFECCIONES_POR_COSTURERO) {
            try {
                // Calculo el rango de tiempo aleatorio
                int rango = MAX_TIEMPO_SIN_COSER - MIN_TIEMPO_SIN_COSER;
                // Creo random y le sumo el mínimo para que entre dentro del rango
                long tiempo = random.nextInt(rango) + MIN_TIEMPO_SIN_COSER;
                // Dormimos el hilo
                Thread.sleep(tiempo);
                // Comprobamos en el almacén si podemos confeccionar;
                almacenTelas.retirarRollos(nombre);
                // Comprobamos en el almacén si podemos almacenar;
                almacenCamisetas.almacenarCamiseta(nombre);
                // Si hemos confeccionamos aumentamos el número de confecciones
                confecciones++;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Cliente "+nombre+" ha completado su máximo de confecciones ("+confecciones+").");
    }
}