package rvt;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;


public class studentuRegistracija{
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Laipni lūgtin studentu reģistrācijā!");
        System.out.println("1. Reģistrēties");
        System.out.println("2. Skatīt visus reģistrētos studentus");

        System.out.println("Ludzu izvelaties savu darbibu.");
        System.out.print("ENTER:");
        int Menu = scanner.nextInt();
        scanner.nextLine();
        
        String csvPath = "src/main/java/rvt/studentuRegistracija.csv";

        switch (Menu) {
            case 1:
                System.out.println("Laipni lugti registracijaa");
                System.out.println("Ludzu ievadeit savus datus!");

                System.out.print("vards(3-15 simbolu garai):");
                String name = scanner.nextLine();
                System.out.println("");
                if (name.length() >= 15 || name.length() <= 3) {
                    System.out.println("[KLUDA] Nepareizi ievadits simbolu garums.\n");
                    scanner.close();
                    return;
                }

                System.out.print("Uzvards(3-20 simbolu garai):");
                String uzvards = scanner.nextLine();
                System.out.println("");
                if (uzvards.length() >= 20 || uzvards.length() <= 3) {
                    System.out.println("[KLUDA] Nepareizi ievadits simbolu garums.");
                    scanner.close();
                    return;
                }

                System.out.print("epasts:");
                String epasts = scanner.nextLine();
                System.out.println("");
                if (!(epasts.contains("@gmail.com") || epasts.contains("@inbox.com"))) {
                    System.out.println("[KLUDA] Tika nepareizi ievadits epasta noformejums");
                    scanner.close();
                    return;
                }

                System.out.print("Perosonas kods:");
                String personasKods = scanner.nextLine();
                System.out.println("");
                if (!personasKods.contains("-") || personasKods.length() < 12) {
                    System.out.println("[KLUDA] perosonas koda noformejums ir ievadits nepareizi");
                    scanner.close();
                    return;
                }

                Student students = new Student(name, uzvards, epasts, personasKods);

                String entry = students.getName() + "," + students.getUzvards() + "," + students.getEpasts() + "," + students.getPerosnasKods();
                File csvFile = new File(csvPath);
                boolean duplicate = false;
                if (csvFile.exists()) {
                    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            if (line.trim().isEmpty()) continue;
                            if (line.toLowerCase().startsWith("name,")) continue; // skip header
                            String[] parts = line.split(",");
                            if (parts.length >= 4 && parts[3].trim().equals(personasKods.trim())) { duplicate = true; break; }
                        }
                    } catch (IOException e) {
                        // ignore and attempt to append
                    }
                }

                if (duplicate) {
                    System.out.println("[INFO] Identical entry already exists — not appending to CSV.");
                    System.out.println("Writing to: " + new java.io.File(csvPath).getAbsolutePath());
                } else {
                    try (FileWriter writer = new FileWriter(csvPath, true)){
                        // create header if file didn't exist
                        if (!csvFile.exists()) {
                            writer.append("name,uzvards,epasts,personasKods\n");
                        }
                        writer.append(entry).append("\n");
                        writer.flush();
                        System.out.println("\n[INFO] Students ir veiksmīgi reģistrējies sistēmā!");
                        System.out.println("Writing to: " + new java.io.File(csvPath).getAbsolutePath());
                        System.out.println("[DEBUG] validations passed — appending to CSV");
                    } catch (IOException e) {
                        System.out.println("Ir notiksuies problema ar sistemu!");
                        e.printStackTrace();
                    }
                }

                break;

            case 2:
                    System.out.println("\n=== VISI REĢISTRĒTIE STUDENTI ===");
                try {
                    System.out.println(Files.readString(Paths.get(csvPath)));
                } catch (IOException e) {
                    System.out.println("[KLUDA] IR NOTIKUSI KLUDA AR STUDENTU IZVADISANU");
                    e.printStackTrace();
                }
        
            default:
                break;
        }

    }
}

class Student{
    String name;
    String uzvards;
    String epasts;
    String personasKods;
    
    Student(String name, String uzvards, String epasts, String personasKods){
        this.name = name;
        this.uzvards = uzvards;
        this.epasts = epasts;
        this.personasKods = personasKods;
    }

    public String getName() { return name;}
    public String getUzvards() {return uzvards;}
    public String getEpasts() {return epasts;}
    public String getPerosnasKods() {return personasKods;}
}