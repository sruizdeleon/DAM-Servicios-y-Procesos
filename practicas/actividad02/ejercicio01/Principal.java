package practicas.actividad02.ejercicio01;

public class Principal {
    public static void main(String[] args) {
        Estanteria estanteria = new Estanteria();

        // Creamos 2 camareros y 2 cocineros
        Camarero c1 = new Camarero("Juan", estanteria);
        Camarero c2 = new Camarero("Pedro", estanteria);
        Cocinero co1 = new Cocinero("Julián", estanteria);
        Cocinero co2 = new Cocinero("Margarita", estanteria);

        // Comienzan a trabajar los empleados
        c1.start();
        c2.start();
        co1.start();
        co2.start();

        try {
            // Esperamos a que acaben los camareros
            c1.join();
            c2.join();
            System.out.println("Fin de jornada Camareros.");

            // Esperamos a que acaben los cocineros
            co1.join();
            co2.join();
            System.out.println("Fin de jornada Cocineros.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // No hay más comandas, cierra el restaurante
        System.out.println("El restaurante ha cerrado.");
    }
}