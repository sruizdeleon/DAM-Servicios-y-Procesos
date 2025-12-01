package practicas.practica01.ejercicio01;

import java.io.File;
import java.io.IOException;

public class Lanzador {

    public static void main(String[] args) {

        ProcessBuilder pb = new ProcessBuilder();

        File directory = new File("D:\\Documentos\\Estudios\\DAM\\03. Segundo curso\\0490-PSP-Programacion de servicios y procesos\\Repositorio\\DAM-Servicios-y-Procesos\\practicas\\practica01\\ejercicio01\\experimentos");

        if(directory.exists() && directory.isDirectory()) {
            directory.delete();
        };

        if(!directory.exists()) {
            directory.mkdir();
        }

        // Comando completo
        pb.command().add("java");
        pb.command().add("D:\\Documentos\\Estudios\\DAM\\03. Segundo curso\\0490-PSP-Programacion de servicios y procesos\\Repositorio\\DAM-Servicios-y-Procesos\\practicas\\practica01\\ejercicio01\\Principal.java");
        pb.command().add("4");
        pb.command().add("2");
        pb.command().add("3");
        pb.command().add("4");
        pb.command().add("5");

        // Reenviar argumentos al Principal
        for (String arg : args) {
            pb.command().add(arg);
        }

        pb.inheritIO();

        try {
            Process p = pb.start();
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("ERROR ejecutando Principal");
        }
    }
}
