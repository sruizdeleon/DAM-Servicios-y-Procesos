package practicas.practica01.ejercicio02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CalculadoraSubproceso {

    public static void main(String[] args) {
        
        // PASO 1: Comprobación de la ruta
        int proceso; // Variable para guardar el proceso por parámetro
        String ruta; // Variable para guardar la ruta por parámetro
        File directorio; // File para guardar directorio
        if(args.length != 2) {
            System.out.println("ERROR: no se ha proporcionado una ruta por argumento.");
            return;
        } else {
            proceso = Integer.valueOf(args[0]); // Guardamos proceso
            ruta = args[1]; // Guardamos ruta
            directorio = new File(ruta); // Creamos directorio

            // Error si el directorio no existe
            if(!directorio.exists()) {
                System.out.println("ERROR: La ruta no existe.");
                return;
            }
            // Error si la ruta no es un directorio
            if (!directorio.isDirectory()) {
                System.out.println("ERROR: La ruta no es un directorio.");
                return;
            }
            // Error si no contiene el directorio ningún archivo
            if (directorio.list().length == 0) {
                System.out.println("ERROR: La ruta no contiene ficheros.");
                return;
            }
        }


        // PASO 2: Lectura de archivos y suma

        int suma = 0; // Variable para acumular la suma
        try {
            // Creamos el buffer para leer el archivo
            BufferedReader br = new BufferedReader(new FileReader(
                ruta+"datos_"+proceso+".txt"
            ));
            try {
                // Mientras siga habiendo líneas lee toda la línea
                while (br.ready()) {
                    try {
                        String linea = br.readLine();
                        int numeroNuevo = Integer.parseInt(linea.trim()); // Trip para eliminar posibles espacios
                        suma = suma + numeroNuevo; // Suma al subtotal
                    } catch (NumberFormatException e) {
                        System.out.println("ERROR: en el formato del número leido en el archivo.");
                    }
                }
                br.close(); // Cerramos el buffer
            } catch (IOException e) {
                System.out.println("ERROR: error al leer el fichero.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("ERROR: en la búsqueda de un archivo.");
        }
        // Mostramos por pantalla solo la suma
        System.out.println(suma);
    }
}
