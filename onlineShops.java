import java.util.HashMap;
import java.util.Set;
public class onlineShops{
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();

        warehouse.addProduct("milk", 3, 10);
        warehouse.addProduct("coffee", 5, 6);
        warehouse.addProduct("buttermilk", 2, 20);
        warehouse.addProduct("yogurt", 2, 20);

        System.out.println("products:");
        for (String product: warehouse.products()) {
            System.out.println(product);
}

        warehouse.addProduct("milk", 3, 10);
        warehouse.addProduct("coffee", 5, 7);

        System.out.println("prices:");
        System.out.println("milk: " + warehouse.price("milk"));
        System.out.println("coffee: " + warehouse.price("coffee"));
        System.out.println("sugar: " + warehouse.price("sugar"));

        System.out.println("stock:");
        System.out.println("coffee:  " + warehouse.stock("coffee"));
        System.out.println("sugar: " + warehouse.stock("sugar"));

        System.out.println("taking coffee " + warehouse.take("coffee"));
        System.out.println("taking coffee " + warehouse.take("coffee"));
        System.out.println("taking sugar " + warehouse.take("sugar"));

        System.out.println("stock:");
        System.out.println("coffee:  " + warehouse.stock("coffee"));
        System.out.println("sugar: " + warehouse.stock("sugar"));

        Item item = new Item("milk", 4, 2);
        System.out.println("an item that contains 4 milks has the total price of " + item.price());
        System.out.println(item);
        item.increaseQuantity();
        System.out.println(item);
    }
}
class Warehouse{
    private HashMap<String, Integer> prices = new HashMap<>();
    private HashMap<String, Integer> stocks = new HashMap<>();
    private HashMap<String, Integer> items = new HashMap<>();

    public void addProduct(String product, int price, int stock){
        prices.put(product, price);
        stocks.put(product, price);
        }
        public int price(String product){
            return prices.getOrDefault(product, -99);
        }
        public int stock(String product){
            return stocks.getOrDefault(product, 0);
        }
        public boolean take(String product){
            if(!stocks.containsKey(product)){
                return false;
            }
            int currentStock = stocks.get(product);

            if (currentStock <= 0) {
            return false;
        }
        stocks.put(product, currentStock - 1);
        return true;
        }
        public Set<String> products(){
            return prices.keySet();
        }
    }

    class Item{
        private String product;
        private int qty;
        private int unitPrice;

        Item(String product, int qty, int unitPrice){
            this.product = product;
            this.qty = qty;
            this.unitPrice = unitPrice;
        }
        public int price(){
            return unitPrice * qty;
        }
        public void increaseQuantity(){
            qty++;
        }
        public String toString(){
            return product + ": " + qty;
        }
}
class shoppingCart{
    private String product;
    private int price;
    int cart;
    shoppingCart(String product, int price, int cart){
        this.product = product;
        this.price = price;
        this.cart = cart;
    }
    public void add(String product, int price){
        cart++;
    }
        
    }



