package practicas.practica01.ejercicio02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CalculadoraSubproceso {
    public static void main(String[] args) {
        
        String ruta;
        File directorio;
        if(args.length != 1) {
            System.out.println("ERROR: no se ha proporcionado una ruta por argumento.");
            return;
        } else {
            ruta = args[0];
            directorio = new File(ruta);
            if(!directorio.exists()) {
                System.out.println("ERROR: La ruta no existe.");
                return;
            } else if (!directorio.isDirectory()) {
                System.out.println("ERROR: La ruta no es un directorio.");
                return;
            } else if (directorio.list().length == 0) {
                System.out.println("ERROR: La ruta no contiene ficheros.");
                return;
            }
        }

        int suma = 0;
        File[] listaArchivos = directorio.listFiles();
        for (File file : listaArchivos) {            
            try {
                BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
                try {
                    while (br.readLine() != null) {
                        try {
                            int numeroNuevo = Integer.parseInt(br.readLine());
                            suma = suma + numeroNuevo;                       
                        } catch (NumberFormatException e) {
                            System.out.println("ERROR: en el formato del número leido en el archivo.");
                        }
                    }
                    br.close();
                } catch (IOException e) {
                    System.out.println("ERROR: error al leer el fichero.");
                }
    
            } catch (FileNotFoundException e) {
                System.out.println("ERROR: en la búsqueda de un archivo.");
            }
        }
        System.out.println(suma);
    }
}
