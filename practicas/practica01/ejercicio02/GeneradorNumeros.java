package practicas.practica01.ejercicio02;

public class GeneradorNumeros {

    public static void main(String[] args) {
        String ruta = "D:\\Documentos\\Estudios\\DAM\\03. Segundo curso\\0490-PSP-Programacion de servicios y procesos\\Repositorio\\DAM-Servicios-y-Procesos\\practicas\\practica01\\ejercicio02\\numerosAleatorios";
        
        if(args.length != 1) {
            System.out.println("ERROR: se tiene que proporcionar solo un número entero positivo por argumento.");
        }
        
        int cantidadDeNumeros;
        try {
            cantidadDeNumeros = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("ERROR: el formato proporcionado no es de número entero positivo.");
        }


        

    }
}
