import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ServicioClima servicioClima = new ServicioClima();

        System.out.println("Introduce el nombre de la ciudad: ");
        String ciudad = scanner.nextLine();

        try{
            String informacionClima = servicioClima.obtenerClima(ciudad);
            System.out.println(informacionClima);
        }catch (Exception e){
            System.err.println("Error obtenido de los datos del clima: " + e.getMessage());
        }

        scanner.close();
    }

}
