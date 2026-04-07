import java.util.ArrayList;
class Interface_in_a_box{
    public static void main(String [] args){
        Box small = new Box(5);
        Box big = new Box(10);

        small.add(new Books("A", "B", 2));

        big.add(small);

        System.out.println(big);

        Box box = new Box(10);

    box.add(new Books("Fyodor Dostoevsky", "Crime and Punishment", 2)) ;
    box.add(new Books("Robert Martin", "Clean Code", 1));
    box.add(new Books("Kent Beck", "Test Driven Development", 0.7));

    box.add(new CD("Pink Floyd", "Dark Side of the Moon", 1973));
    box.add(new CD("Wigwam", "Nuclear Nightclub", 1975));
    box.add(new CD("Rendezvous Park", "Closer to Being Here", 2012));

    System.out.println(box);
    Books book1 = new Books("Fyodor Dostoevsky", "Crime and Punishment", 2);
    Books book2 = new Books("Robert Martin", "Clean Code", 1);
    Books book3 = new Books("Kent Beck", "Test Driven Development", 0.5);

    CD cd1 = new CD("Pink Floyd", "Dark Side of the Moon", 1973);
    CD cd2 = new CD("Wigwam", "Nuclear Nightclub", 1975);
    CD cd3 = new CD("Rendezvous Park", "Closer to Being Here", 2012);

    System.out.println(book1.toString());
    System.out.println(book2);
    System.out.println(book3);
    System.out.println(cd1);
    System.out.println(cd2);
    System.out.println(cd3);

    }
}

class Books implements Packable{
    String author;
    String bookName;
    double bookWeight;
    Books(String author, String bookName, double bookWeight){
        this.author = author;
        this.bookName = bookName;
        this.bookWeight = bookWeight;
    }
    public double weight(){
    return bookWeight;
}
@Override
public String toString(){
    return author + ": " + bookName + " (" + bookWeight + " kg)";
}

}
class CD implements Packable{
    String artist;
    String nameCD;
    int publicationYear;
    CD(String artist, String nameCD, int publicationYear){
        this.artist = artist;
        this.nameCD = nameCD;
        this.publicationYear = publicationYear;
    }
    public double weight(){
        return 0.1;
    }
    @Override
    public String toString(){
    return artist + ": " + nameCD + " (" + publicationYear + ")";
}
}
class Box implements Packable{
    private double capacity;
    private ArrayList<Packable> items;
    Box(double capacity){
        this.capacity = capacity;
        this.items = new ArrayList<>();
    }
    public double weight(){
       double weight = 0;
       for (Packable p :items){
            weight+= p.weight();
       }
        return weight;
 
    }
    
    double totalWeight(){
        double sum = 0;

        for (Packable p : items){
            sum += p.weight();
        }
        return sum;
    }
    void add(Packable item){
        if(totalWeight() + item.weight() <= capacity){
            items.add(item);
        }
    }
    @Override
    public String toString(){
        return "Box: " +items.size()+" items, total weight: " +totalWeight()+". The boxes weight is "+ weight()+" kilos";
    }
    

}