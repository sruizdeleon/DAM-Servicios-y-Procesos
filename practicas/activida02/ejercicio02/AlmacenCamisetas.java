package practicas.activida02.ejercicio02;

public class AlmacenCamisetas {
    private final int MAX_CAMISETAS = 10;
    private int totalCamisetas = 0;

    public synchronized void almacenarCamiseta(String nombreCosturero) {
        while (totalCamisetas >= MAX_CAMISETAS) {
            try {
                // Espera por estar el almacén de camisetas lleno
                System.out.println("Almacén de Camisetas lleno. "+nombreCosturero+" espera a poder almacenar más camisetas.");
                // Los costureros esperan
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // Añadimos una camiseta al almacén
        totalCamisetas = totalCamisetas + 1;

        // Mensaje para avisar del almacenado de una camiseta
        System.out.println(nombreCosturero + " confecciona camiseta -> Total camisetas en almacén: " + totalCamisetas);

        // Avisamos a los clientes que vuelve a haber stock del producto
        notifyAll();
    }

    public synchronized void comprarCamiseta(String nombreCliente) {
        while (totalCamisetas == 0) {
            try {
                // Espera a que haya stock de camisetas para comprar
                System.out.println("Almacén de Camisetsas vacío. "+nombreCliente+" espera a que haya de nuevo stock.");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // Vendemos la camiseta
        totalCamisetas = totalCamisetas - 1;

        // Registramos qué cliente ha comprado
        System.out.println(nombreCliente + " retira camiseta -> Total camisetas en almacén: " + totalCamisetas);

        // Notificamos a los costureros
        notifyAll();
    }

}