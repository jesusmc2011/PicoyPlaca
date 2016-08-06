import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Scanner input = new Scanner(System.in);
        System.out.println("Introduzca el numero de placa por favor!!");
        String plateinput = input.nextLine();
        System.out.println("Introduzca la fecha  en formato yyyy-MM-dd ");
        String date = input.nextLine();
        System.out.println("Introduzca la hora  en formato HH:mm ");
        String time = input.nextLine();

        Plate plate = new Plate(plateinput);
        plate.canRoad(date, time);

//      date = 2016-08-05
//      time = 10:30
    }
}
