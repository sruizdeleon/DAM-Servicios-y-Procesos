package practicas.practica01.ejercicio02;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;

public class Principal {

    public static void main(String[] args) {

        // Ruta de carpeta para crear los archivos de números aleatorios
        File directory = new File("practicas\\practica01\\ejercicio02\\numerosAleatorios\\");

        // Comprobamos si existe la ruta y si es un directorio
        // Si existe lo borramos y con todos sus archivos dentro para resetear
        if(directory.exists() && directory.isDirectory()) {
            directory.delete();
        }

        // Si no existe o lo hemos borrado creamos el directorio
        if(!directory.exists()) {
            directory.mkdir();
        }

        // Creamos un vector con la cantidad de números aleatorios por proceso
        Integer[] numerosAleatorios = {3, 4};

        // Creamos la ruta para ejecutar Generador de Números
        String rutaGeneradorNumeros = "practicas\\practica01\\ejercicio02\\GeneradorNumeros.java";
        int proceso = 1; // Variable para contar los procesos
        ArrayList<Process> procesosGenerados = new ArrayList<>(); // Vector para almacenar procesos

        // PASO 1: Generador Números
        for (int numero : numerosAleatorios) {
            System.out.println("Creando procesos: "+proceso);
            // Creando el proceso y los parámetros a enviar
            ProcessBuilder pb = new ProcessBuilder();
            
            // Añadimos comandos y parámetros
            pb.command().add("java");
            pb.command().add(rutaGeneradorNumeros);
            pb.command().add(String.valueOf(proceso));
            pb.command().add(String.valueOf(numero));

            pb.inheritIO(); 

            try {
                // Lanzamos el proceso
                procesosGenerados.add(pb.start());
            } catch (IOException e) {
                System.out.println("ERROR: al iniciar el proceso.");
                return;
            }
            proceso++; // Aumentamos el número de proceso
        }


        // PASO 2: Esperar a terminar procesos de Generador Números

        proceso = 1; // reinicio de contador de procesos
        for (Process p : procesosGenerados) {
            try {
                p.waitFor(); // espera del proceso
            } catch (InterruptedException e) {
                System.out.println("ERROR: error en la espera del proceso.");
                return;
            }
            proceso++;
        }
        

        // PASO 3: Calculadora Subproceso

        int sumaTotal = 0; // variable para guardar la suma total
        for (int p = 1; p <= numerosAleatorios.length; p++) {
            System.out.println("Creando proceso: "+p); 
            // Creación de proceso
            ProcessBuilder pb = new ProcessBuilder();

            // Añadimos los comandos y parámetros
            pb.command().add("java");
            pb.command().add("practicas\\practica01\\ejercicio02\\CalculadoraSubproceso.java");
            pb.command().add(String.valueOf(p));
            pb.command().add("practicas\\practica01\\ejercicio02\\numerosAleatorios\\");

            // Redirección de la salida
            pb.redirectOutput();


            Process process = null;
            try {
                process = pb.start(); // Iniciamos el proceso
            } catch (IOException e) {
                e.printStackTrace();
            }

            
            try {
                // Creamos el buffer para leer el la salida
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    try {
                            // Leemos la salida del proceso
                            String numeroLeido = line.trim();
                            sumaTotal = sumaTotal + Integer.valueOf(numeroLeido); // Lo sumamos al total
                    } catch (NumberFormatException e) {
                        System.out.println("ERROR: en el formato de lectura de la suma del subproceso");
                    }
                }
            } catch (IOException e) {
                System.err.println("ERROR: en la lectura del proceso.");
            }

        }
        System.out.println("Suma total: " + sumaTotal);
    }
}