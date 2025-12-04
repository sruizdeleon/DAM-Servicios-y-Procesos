package practicas.practica01.ejercicio01;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GeneradorExperimentos {

    public static void main(String[] args) {

        // Comprobamos recibir dos parámentros
        if (args.length != 2) {
            System.err.println("ERROR: Se requieren 2 argumentos: <num_proceso> <num_experimentos>");
            return;
        }

        // Variables para guardar el proceso y el número de experimentos
        int numProceso;
        int numExperimentos;

        // Guardamos los parámetros
        try {
            numProceso = Integer.parseInt(args[0]);
            numExperimentos = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("ERROR: Ambos argumentos deben ser enteros.");
            return;
        }

        // Creamos la ruta al archivo a leer
        String nombreArchivo = "practicas\\practica01\\ejercicio01\\experimentos\\experimentos_proceso_" + numProceso + ".txt";

        // Creamos el buffer de escritura
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            // Rellenamos con la cantidad de experimentos solicitada
            for (int i = 1; i <= numExperimentos; i++) {
                bw.write("Experimento-" + i);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("ERROR al escribir el archivo del proceso " + numProceso);
        }
    }
}
