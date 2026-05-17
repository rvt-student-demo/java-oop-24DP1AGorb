package rvt;

import java.io.File;
import java.util.Scanner;

public class Orders{
    public static void main(String[] args){

    double kopaSumma = 0;

    try{

        File file = new File("data\\orders.csv");
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()){
            String rinda = scanner.nextLine();
            String[] dati = rinda.split(",");

            int orderId = Integer.parseInt(dati[0]);
            String klients = dati[1];
            String produkts = dati[2];
            int daudzums =Integer.parseInt(dati[3]);
            double cena = Double.parseDouble(dati[4]);

            double summa = daudzums * cena;
            kopaSumma += summa;

            System.out.println("Pasūtījums #" + orderId +":"+klients+" pasūtīja " + daudzums + " x " + produkts + " (" + cena+" EUR -> kopā:) "+ summa);
        }
        System.out.println("Kopējā pasūtījuma summa: " + kopaSumma + " EUR");

    }
    catch(Exception e){
        System.out.println("Nolasīta kļūda");
    }
}
}
