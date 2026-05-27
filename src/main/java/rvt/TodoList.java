package rvt;
import java.util.Scanner;
import java.util.ArrayList;

public class TodoList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();

        while (true) {
            System.out.print("Command: ");
            String command = scanner.nextLine().toUpperCase();
            if(command.equals("ADD")){
                System.out.print("Task: ");
                list.add(scanner.nextLine());
            }
            if(command.equals("LIST")){
                for(int i = 0; i < list.size(); i++){
                    System.out.println((i+1) + ": " + list.get(i));
                }
                        }
            if(command.equals("COMPLETED")){
                System.out.print("Which one is completed? ");
                int index = Integer.parseInt(scanner.nextLine());
                if(index > 0 && index <= list.size()){
                    list.remove(index-1);
                }
            }
            if(command.equals("EXIT")){
                break;
            }
        }
    }
}
