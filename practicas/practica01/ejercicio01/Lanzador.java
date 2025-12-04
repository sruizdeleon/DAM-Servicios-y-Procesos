package practicas.practica01.ejercicio01;

import java.io.File;
import java.io.IOException;

public class Lanzador {

    public static void main(String[] args) {

        // Guardamos ruta de guardado de experimentos
        File directory = new File("practicas\\practica01\\ejercicio01\\experimentos\\");

        // Comprobamos que exista y sea un directorio
        if(directory.exists() && directory.isDirectory()) {
            directory.delete(); // Lo borramos junto a sus archivos
        };

        // Creamos de nuevo el directorio esta vez vacío
        if(!directory.exists()) {
            directory.mkdir();
        }

        // Creamos el constructor del proceso
        ProcessBuilder pb = new ProcessBuilder();

        // Añadimos los comandos y parámetros
        pb.command().add("java");
        pb.command().add("practicas\\practica01\\ejercicio01\\Principal.java");
        pb.command().add("3");
        pb.command().add("2");
        pb.command().add("3");
        pb.command().add("4");


        pb.inheritIO();

        try {
            Process p = pb.start();
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("ERROR ejecutando Principal");
        }
    }
}
