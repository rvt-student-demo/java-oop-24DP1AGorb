public class Orders{
    public static void main(String[] args){

    double kopaSumma = 0;

    try{

        File file = new File("data\\orders.csv");
        while(true){
            String[] dati = rinda.spilt(",");

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
