package practicas.practica01.ejercicio02;

import java.io.IOException;
import java.util.ArrayList;

public class Principal {

    public static void main(String[] args) {
        String[] numerosAleatorios = {"3", "4"};
        String rutaGeneradorNumeros = "D:\\Documentos\\Estudios\\DAM\\03. Segundo curso\\0490-PSP-Programacion de servicios y procesos\\Repositorio\\DAM-Servicios-y-Procesos\\practicas\\practica01\\ejercicio02\\GeneradorNumeros.java";

        ArrayList<Process> procesos = new ArrayList<>();
        for (String numero : numerosAleatorios) {
            ProcessBuilder pb = new ProcessBuilder(
                "java",
                rutaGeneradorNumeros,
                numero
            );
            try {
                procesos.add(pb.start());
            } catch (IOException e) {
                System.out.println("ERROR: al iniciar el proceso.");
                return;
            }
        }

        for (Process proceso : procesos) {
            try {
                proceso.waitFor();
            } catch (InterruptedException e) {
                System.out.println("ERROR: error en la espera del proceso.");
                return;
            }
        }
        
        ArrayList<Process> procesosSuma = new ArrayList<>();
        for (int proceso = 0; proceso < numerosAleatorios.length; proceso++) {
            ProcessBuilder pb = new ProcessBuilder(
                "java",
                "",
                ""
            );
            pb.start();
            pb.redirectOutput()
        }


    }
}
