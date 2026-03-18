package rvt;
import java.util.HashMap;
public class Warehouse{

    HashMap<String, Integer> prices = new HashMap<>(){
        this.price.put(product, String.valueOf(price));
    }
    HashMap<String, Integer> stocks = new HashMap<>();
    public static void main(String[] args){
        Warehouse warehouse = new Warehouse();
        warehouse.addProduct("milk", 3, 10);
        warehouse.addProduct("coffee", 5, 7);

        System.out.println("prices:");
        System.out.println("milk: " + warehouse.prices("milk"));
        System.out.println("coffee: " + warehouse.prices("coffee"));
        System.out.println("sugar: " + warehouse.prices("sugar"));
    }

public void addProduct(String product, int price, int stock){
    prices.put(product, price);
    stocks.put(product, stock);
}
public String prices(String product){
    if(!prices.containsKey(product)){
        return "Null";
    }
    return product;
}
}