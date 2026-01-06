package rvt;
import java.util.InputMismatchException;
import java.util.Scanner;
public class tryCatch{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while(true){
            try{
                System.out.println("Enter a number:");
                int i = scanner.nextInt();
                System.out.println("You entered: "+ i);
        }
        catch(InputMismatchException e){
            System.out.println("WITH NUMBER ONLY IDIOT!");
            scanner.next();
        }
    }

        }
        
}