package practicas.practica01.ejercicio02;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GeneradorNumeros {

    public static void main(String[] args) {
        // Variable con ruta para saber en qué carpeta guardar los archivos
        String ruta = "practicas\\practica01\\ejercicio02\\numerosAleatorios\\";
        
        // Dos parámetros: proceso y número de números aleatorios a crear
        if(args.length != 2) {
            System.out.println("ERROR: se tiene que proporcionar el número de proceso y el número de números aleatorios a crear.");
            return;
        }
        
        int proceso; // Variable para proceso
        int numerosACrear; // Variable para números aleatorios
        try {
            proceso = Integer.parseInt(args[0]);
            numerosACrear = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("ERROR: el formato proporcionado no es de número entero positivo.");
            return;
        }

        // Componemos la ruta final de búsqueda del archivo
        String rutaCompleta = ruta + "datos_" + proceso + ".txt";
        try {
            // Creamos un buffer de escritura
            BufferedWriter br = new BufferedWriter(new FileWriter(rutaCompleta));
            // Bucle para cada número a crear
            for (int n = 1; n <= numerosACrear; n++) {
                // Creamos el número
                int numeroAleatorio = (int) Math.floor(Math.random()*100);
                // Lo escribimos en el fichero
                br.write(String.valueOf(numeroAleatorio));
                // Si es el último número no le incluimos salto de línea
                if(n != numerosACrear){
                    br.write("\n");
                }
            }
            br.close(); // Cerramos el buffer
            System.out.println("====== Numeros aleatorios guardados en archivo: datos_"+proceso+".txt ======");
        } catch (IOException e) {
            System.out.println("ERROR: no se ha podido crear correctamente el archivo.");
        }
    }

}
