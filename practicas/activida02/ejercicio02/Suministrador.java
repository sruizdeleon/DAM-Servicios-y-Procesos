package practicas.activida02.ejercicio02;

import java.util.Random;

public class Suministrador extends Thread {

    private final int MAX_ROLLOS_POR_SUMINISTRADOR = 20;
    private final int MAX_TIEMPO_SIN_SUMINISTRAR = 300;
    private final int MIN_TIEMPO_SIN_SUMINISTRAR = 100;
    private AlmacenTelas almacenTelas;
    private String nombre;
    private int rollos = 0;
    private Random random = new Random();

    public Suministrador(String nombre, AlmacenTelas almacenTelas){
        this.nombre = nombre;
        this.almacenTelas = almacenTelas;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        while (rollos < MAX_ROLLOS_POR_SUMINISTRADOR) {
            try {
                // Calculo el rango de tiempo aleatorio
                int rango = MAX_TIEMPO_SIN_SUMINISTRAR - MIN_TIEMPO_SIN_SUMINISTRAR;
                // Creo random y le sumo el mínimo para que entre dentro del rango
                long tiempo = random.nextInt(rango) + MIN_TIEMPO_SIN_SUMINISTRAR;
                // Dormimos el hilo
                Thread.sleep(tiempo);
                // Comprobamos en el almacén si podemos proveer rollos;
                almacenTelas.colocarRollo(nombre);
                // Si hemos proveído aumentamos el número de rollos
                rollos++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Suministrador "+nombre+" ha completado su máximo de rollos ("+rollos+").");
    }
}