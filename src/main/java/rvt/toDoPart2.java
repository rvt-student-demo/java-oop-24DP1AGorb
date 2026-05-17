package rvt;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
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
            Scanner scanner = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally{
            System.out.println("Hi");
        }
        
    }
}