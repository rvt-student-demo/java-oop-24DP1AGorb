package rvt;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
public class toDoPart2{
    public static void main(String[] args) {
        
    }
    private ArrayList<String> tasks;
    //path to the CSV file
    private final String filePath = "toDoPart2.csv";

    public toDoPart2(){
        this.tasks = new ArrayList<>();
        loadFromFile();
    }

    private void loadFromFile(){
        try{
            Scanner reader = new Scanner(new File(filePath));
        }
        finally{
            System.out.println("Hi");
        }
        
    }
}