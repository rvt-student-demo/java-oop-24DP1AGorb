
package rvt;

public class inTheBox{
    public static void main(String[] args){
        Box box = new Box(10);

        box.add(new Book("Fyodor Dostoevsky", "Crime and Punishment", 2)) ;
        box.add(new Book("Robert Martin", "Clean Code", 1));
        box.add(new Book("Kent Beck", "Test Driven Development", 0.7));
    
        box.add(new CD("Pink Floyd", "Dark Side of the Moon", 1973));
        box.add(new CD("Wigwam", "Nuclear Nightclub", 1975));
        box.add(new CD("Rendezvous Park", "Closer to Being Here", 2012));
    
        System.out.println(box);
    }
}

class Box{
    private int capacity;
    private java.util.ArrayList<Packable> items;
    private double totalWeight;

    public Box(int capacity){
        this.capacity = capacity;
        this.items = new java.util.ArrayList<>();
        this.totalWeight = 0.0;
    }

    public void add(Packable p){
        if(this.items.size() < this.capacity){
            this.items.add(p);
            this.totalWeight += p.weight();
        }
    }

    public String toString(){
        return "Box: " + this.items.size() + " items, total weight " + this.totalWeight;
    }
}

class CD implements Packable{
    private String artist;
    private String name;
    private int year;

    public CD(String artist, String name, int year){
        this.artist = artist;
        this.name = name;
        this.year = year;
    }

    public double weight(){
        return 0.1; // lightweight default
    }

    public String toString(){
        return this.artist + " : " + this.name + " (" + this.year + ")";
    }
}