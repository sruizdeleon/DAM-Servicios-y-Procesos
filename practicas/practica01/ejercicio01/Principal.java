package practicas.practica01.ejercicio01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Principal {

    public static void main(String[] args) {

        // Comprobamos que recibamos los dos
        if (args.length < 2) {
            System.out.println("ERROR: no se ha pasado mínimo un parámetro de procesos y número de experimentos");
            return;
        }

        // Variable para guardar el número de procesos
        int numProcesos;
        
        // Comprobamos que sea un número entero
        try {
            numProcesos = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("ERROR: El primer argumento debe ser un entero.");
            return;
        }

        // Comprobamos que el número de procesos corresponda con la longuitud del resto de parámetros
        if (args.length != numProcesos + 1) {
            System.out.println("ERROR: Debe indicar " + numProcesos + " cantidades de experimentos.");
            return;
        }

        // Creamos un array para guardar los procesos
        ArrayList<Process> procesos = new ArrayList<>();

        System.out.println("Generando experimentos desde " + numProcesos + " procesos...");

        // Lanzamos los procesos GeneradorExperimentos
        for (int i = 1; i <= numProcesos; i++) {

            // Variable para guardar el nýmero de experimentos
            int cantExperimentos;

            // Guardamos el parámetro correspondiente
            try {
                cantExperimentos = Integer.parseInt(args[i]);
            } catch (NumberFormatException e) {
                System.out.println("ERROR: El argumento " + args[i] + " no es un número.");
                return;
            }

            // Creamos el proceso
            ProcessBuilder pb = new ProcessBuilder();

            // Añadimos los comandos y parámetros
            pb.command().add("java");
            pb.command().add("practicas\\practica01\\ejercicio01\\GeneradorExperimentos.java");
            pb.command().add(String.valueOf(i));
            pb.command().add(String.valueOf(cantExperimentos));

            try {
                procesos.add(pb.start()); // Lanzamos el proceso
            } catch (IOException e) {
                System.out.println("ERROR al lanzar proceso " + i);
            }
        }

        // Esperamos a que todos los procesos acaben
        for (Process p : procesos) {
            try {
                p.waitFor();
            } catch (InterruptedException e) {
                System.out.println("ERROR esperando un proceso");
            }
        }

        // Leemos los resultados
        for (int i = 1; i <= numProcesos; i++) {
            System.out.println("\n--- Resultados del PROCESO " + i + " ---");

            String archivo = "practicas\\practica01\\ejercicio01\\experimentos\\experimentos_proceso_" + i + ".txt";

            // Creamos el buffer de lectura
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea); // Mostramos por pantalla el experimento
                }
            } catch (IOException e) {
                System.out.println("ERROR leyendo archivo del proceso " + i);
            }
        }
    }
}
