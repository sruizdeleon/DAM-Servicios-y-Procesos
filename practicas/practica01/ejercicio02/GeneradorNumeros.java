package practicas.practica01.ejercicio02;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GeneradorNumeros {

    private static int proceso = 1;

    public static void main(String[] args) {
        String ruta = "practicas\\practica01\\ejercicio02\\numerosAleatorios\\";
        
        if(args.length != 1) {
            System.out.println("ERROR: se tiene que proporcionar el número de números aleatorios a crear.");
            return;
        }
        
        int cantidadDeNumeros;
        try {
            cantidadDeNumeros = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("ERROR: el formato proporcionado no es de número entero positivo.");
            return;
        }

        String rutaCompleta = ruta + "datos_" + proceso + ".txt";
        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(rutaCompleta));
            for (int n = 1; n <= cantidadDeNumeros; n++) {
                int numeroAleatorio = (int) Math.floor(Math.random()*100);
                br.write(numeroAleatorio+"\n");
            }
            br.close();
        } catch (IOException e) {
            System.out.println("ERROR: no se ha podido crear correctamente el archivo.");
        }
        proceso++;
    }

}
