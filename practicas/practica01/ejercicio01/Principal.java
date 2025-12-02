package practicas.practica01.ejercicio01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Principal {

    public static void main(String[] args) {

        if (args.length < 2) {
            System.err.println("ERROR: Uso: <num_procesos> <exp1> <exp2> ...");
            return;
        }

        int numProcesos;

        try {
            numProcesos = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("ERROR: El primer argumento debe ser un entero.");
            return;
        }

        if (args.length != numProcesos + 1) {
            System.err.println("ERROR: Debe indicar " + numProcesos + " cantidades de experimentos.");
            return;
        }

        ArrayList<Process> procesos = new ArrayList<>();

        System.out.println("Generando experimentos desde " + numProcesos + " procesos...");

        // Lanzar procesos GeneradorExperimentos
        for (int i = 1; i <= numProcesos; i++) {

            int cantExperimentos;

            try {
                cantExperimentos = Integer.parseInt(args[i]);
            } catch (NumberFormatException e) {
                System.err.println("ERROR: El argumento " + args[i] + " no es un número.");
                return;
            }

            // Llamada correcta a la clase con paquete y classpath
            ProcessBuilder pb = new ProcessBuilder(
                    "java",
                    "practicas\\practica01\\ejercicio01\\GeneradorExperimentos.java",
                    String.valueOf(i),
                    String.valueOf(cantExperimentos)
            );

            try {
                procesos.add(pb.start());
            } catch (IOException e) {
                System.err.println("ERROR al lanzar proceso " + i);
            }
        }

        // Esperar a todos los procesos
        for (Process p : procesos) {
            try {
                p.waitFor();
            } catch (InterruptedException e) {
                System.err.println("ERROR esperando un proceso");
            }
        }

        // Leer resultados
        for (int i = 1; i <= numProcesos; i++) {
            System.out.println("\n--- Resultados del PROCESO " + i + " ---");

            String archivo = "practicas\\practica01\\ejercicio01\\experimentos\\experimentos_proceso_" + i + ".txt";

            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
            } catch (IOException e) {
                System.err.println("ERROR leyendo archivo del proceso " + i);
            }
        }
    }
}
