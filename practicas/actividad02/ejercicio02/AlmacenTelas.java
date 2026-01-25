package practicas.actividad02.ejercicio02;

public class AlmacenTelas {
    private final int MAX_ROLLOS = 20;
    private int totalRollos = 0;

    public synchronized void colocarRollo(String nombreSuministrador) {
        while (totalRollos >= MAX_ROLLOS) {
            try {
                // Espera por estar el almacén de telas lleno
                System.out.println("Almacén de Telas lleno. "+nombreSuministrador+" espera a poder dejar más rollos.");
                // Los suministradores esperan
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // Añadimos un rollo de tela al almacén
        totalRollos = totalRollos + 1;

        // Mensaje para avisar de la retirada de rollos
        System.out.println(nombreSuministrador + " deja rollo de tela -> Total rollos en almacén: " + totalRollos);

        // Avisamos a los costureros
        notifyAll();
    }

    public synchronized void retirarRollos(String nombreCosturero) {
        while ((totalRollos - 2) < 0) {
            try {
                // Espera a que haya más rollos para confeccionar camisetas
                System.out.println("Almacén de Telas vacío o sin suficientes rollos para una camiseta. "+nombreCosturero+" espera a que haya más rollos.");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // Retiramos los rollos
        totalRollos = totalRollos - 2;

        // Avisamos qué costurero lo ha retirado
        System.out.println(nombreCosturero + " retira 2 rollos de tela -> Total rollos en almacén: " + totalRollos);

        // Notificamos a los suministradores
        notifyAll();
    }

}
