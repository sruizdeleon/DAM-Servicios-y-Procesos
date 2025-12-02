package practicas.practica01.ejercicio01;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GeneradorExperimentos {

    public static void main(String[] args) {

        // Debe recibir exactamente 2 argumentos
        if (args.length != 2) {
            System.err.println("ERROR: Se requieren 2 argumentos: <num_proceso> <num_experimentos>");
            return;
        }

        int numProceso;
        int numExperimentos;

        try {
            numProceso = Integer.parseInt(args[0]);
            numExperimentos = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("ERROR: Ambos argumentos deben ser enteros.");
            return;
        }

        // Carpeta relativa
        String ruta = "practicas\\practica01\\ejercicio01\\experimentos\\";
        String nombreArchivo = ruta + "experimentos_proceso_" + numProceso + ".txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (int i = 1; i <= numExperimentos; i++) {
                bw.write("Experimento-" + i);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("ERROR al escribir el archivo del proceso " + numProceso);
        }
    }
}
