package rvt;
import java.util.Scanner;
import java.util.ArrayList;

public class todo {
public static void main(String[] args) {

try {
Scanner scanner = new Scanner(System.in);
// Create an ArrayList to store todo items (strings)
ArrayList list = new ArrayList<>();

list.add("read the course material");
list.add("watch the latest fool us");
list.add("take it easy");
// Infinite loop so the program keeps running
while (true) {
// Loop through the list to display each item
for (int i = 0; i < list.size(); i++) {

// Print item number (i + 1) and the task text
// i starts at 0, humans start at 1
System.out.println((i + 1) + ". " + list.get(i));
}
// Ask the user which item they want to remove
System.out.println("Pick a number to remove (0 to stop):");
// Read the user's input as a full line and convert it to an integer
// nextLine() avoids input bugs caused by nextInt()
int choice = Integer.valueOf(scanner.nextLine());
// If the user enters 0, exit the loop and end the program

if (choice == 0) {
break;
}
// Convert human numbering (1,2,3...) to list index (0,1,2...)
int index = choice - 1;
// Make sure the index is valid before removing
if (index >= 0 && index < list.size()) {
// Remove the selected item from the list
list.remove(index);
}
// Print a blank line for cleaner output
System.out.println();
}
// Catch any exception that happens inside the try block
} catch (Exception e) {
// Print an error message instead of crashing
System.out.println("Error");
}
}
}