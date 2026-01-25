package practicas.actividad02.ejercicio02;

/**
 * Ejercicio 2:
 * Se desea simular el funcionamiento concurrente de una fábrica de
 * camisetas, en la que intervienen tres tipos de hilos, que deben sincronizarse mediante
 * monitores.
 */

public class Fabrica {
    public static void main(String[] args) {
        // Creamos los almacenes
        AlmacenTelas almacenTelas = new AlmacenTelas();
        AlmacenCamisetas almacenCamisetas = new AlmacenCamisetas();

        // Creamos los objetos con los roles
        Suministrador s1 = new Suministrador("Telas Juan", almacenTelas);
        Suministrador s2 = new Suministrador("Tejidos Loli", almacenTelas);
        Costurero co1 = new Costurero("Pepa", almacenTelas, almacenCamisetas);
        Costurero co2 = new Costurero("Josefina", almacenTelas, almacenCamisetas);
        Cliente ci1 = new Cliente("Zara", almacenCamisetas);
        Cliente ci2 = new Cliente("Mango", almacenCamisetas);

        // Abre la fábrica y comienza la cadena de producción
        s1.start();
        s2.start();
        co1.start();
        co2.start();
        ci1.start();
        ci2.start();

        try {
            // Esperamos a que finalicen los Suministradores
            s1.join();
            s2.join();
            System.out.println("Suministradores terminan de proveer.");

            // Esperamos a que finalicen los costureros
            co1.join();
            co2.join();
            System.out.println("Costureros terminan de confeccionar.");

            // Esperamos a que finalicen los clientes
            ci1.join();
            ci2.join();
            System.out.println("Clientes terminan de comprar.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Cierre de la fábrica.");
    }
}
