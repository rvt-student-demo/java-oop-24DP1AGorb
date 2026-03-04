public interface Packable{
    double weight();
}
public class Book implements Packable{

    private String author;
    private String name;
    private double weight;

    public Book(String author, String name, double weight){
        this.author = author;
        this.name = name;
        this.weight = weight;

        }
         public returnType weight(){
            return weight;
    }
}