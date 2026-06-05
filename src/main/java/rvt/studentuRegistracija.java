package rvt;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.Paths;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;


public class studentuRegistracija{
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Laipni lūgtin studentu reģistrācijā!");
        System.out.println("1. Reģistrēties");
        System.out.println("2. Skatīt visus reģistrētos studentus");
        System.out.println("3. Izdzēst lietotāju");
        System.out.println("4. Rediģēt lietotāja datus (ievadiet personas kodu)");
        System.out.println("5. Exit (apturēt programmu)");

        System.out.println("Ludzu izvelaties savu darbibu.");
        System.out.print("ENTER:");
        int Menu = scanner.nextInt();
        scanner.nextLine();
        
        String csvPath = "src/main/java/rvt/studentuRegistracija.csv";
        Student studentuRegistracija = null;

        switch (Menu) {
            case 1: //CASE 1 - REĢISTRĒTIES
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

                String entry = students.getName() + "," + students.getUzvards() + "," + students.getEpasts() + "," + students.getPersonasKods();
                File csvFile = new File(csvPath);
                // Robust, non-destructive duplicate check using Sets (trim, lowercase, skip header)
                Set<String> existingEmails = new HashSet<>();
                Set<String> existingPcodes = new HashSet<>();
                if (csvFile.exists()) {
                    try (BufferedReader br2 = new BufferedReader(new FileReader(csvFile))) {
                        String l;
                        while ((l = br2.readLine()) != null) {
                            l = l.trim();
                            if (l.isEmpty()) continue;
                            String[] p = l.split(",");
                            if (p.length < 3) continue;
                            String e = p[2].trim().replaceAll("^\"|\"$", "").toLowerCase();
                            if ("epasts".equalsIgnoreCase(e)) continue; // header
                            existingEmails.add(e);
                            if (p.length >= 4) {
                                String pc = p[3].trim().replaceAll("^\"|\"$", "");
                                if (!"personasKods".equalsIgnoreCase(pc)) existingPcodes.add(pc);
                            }
                        }
                    } catch (IOException e) {
                        // ignore - best-effort only
                    }
                }

                String normalizedEmail = epasts.trim().toLowerCase();
                String normalizedPcode = personasKods.trim();
                if (existingEmails.contains(normalizedEmail)) {
                    System.out.println("[KLUDA] Epasts jau reģistrēts: " + epasts);
                    scanner.close();
                    return;
                }
                if (existingPcodes.contains(normalizedPcode)) {
                    System.out.println("[KLUDA] Personas kods jau reģistrēts: " + personasKods);
                    scanner.close();
                    return;
                }

                // No duplicate found — append
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
                

                break;

            case 2: // Visi rēģistrētie studenti
                    System.out.println("\n=== VISI REĢISTRĒTIE STUDENTI ===");
                try {
                    System.out.println(Files.readString(Paths.get(csvPath)));
                } catch (IOException e) {
                    System.out.println("[KLUDA] IR NOTIKUSI KLUDA AR STUDENTU IZVADISANU");
                    e.printStackTrace();
                }
                break;
            case 3: // Izdzēst lietotāju
                System.out.println("Studenta izdzēšana");
                System.out.print("Ievadiet Studenta epastu vai Personas kodu: ");
                String toDelete = scanner.nextLine().trim();

                File csvFile3 = new File(csvPath);
                if (!csvFile3.exists()) {
                    System.out.println("[KLUDA] CSV fails nav atrasts: " + csvFile3.getAbsolutePath());
                    break;
                }

                File backup = new File(csvPath + ".bak");
                try {
                    Files.copy(csvFile3.toPath(), backup.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    System.out.println("[KLUDA] Nevar izveidot backup failu: " + backup.getAbsolutePath());
                    e.printStackTrace();
                    break;
                }

                File temp = new File(csvPath + ".tmp");
                boolean found = false;
                try (BufferedReader br = new BufferedReader(new FileReader(csvFile3));
                    FileWriter fw = new FileWriter(temp)) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String l = line.trim();
                        if (l.isEmpty()) { fw.write(line + System.lineSeparator()); continue; }
                        String[] p = l.split(",");
                        if (p.length >= 3) {
                            String e = p[2].trim().replaceAll("^\"|\"$", "");
                            String pc = p.length >= 4 ? p[3].trim().replaceAll("^\"|\"$", "") : "";
                            if (e.equalsIgnoreCase(toDelete) || pc.equals(toDelete)) {
                                found = true;
                                continue; // skip this line (delete)
                            }
                        }
                        fw.write(line + System.lineSeparator());
                    }
                } catch (IOException e) {
                    System.out.println("[KLUDA] Kļūda lasot vai rakstot pagaidu failu");
                    e.printStackTrace();
                    // attempt to restore backup
                    break;
                }

                if (!found) {
                    // nothing deleted — remove temp and keep original
                    temp.delete();
                    System.out.println("[INFO] Ieraksts netika atrasts: " + toDelete);
                } else {
                    try {
                        Files.move(temp.toPath(), csvFile3.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("[INFO] Students ar identifikatoru '" + toDelete + "' tika izdzēsts.");
                        System.out.println("Backup saglabāts: " + backup.getAbsolutePath());
                    } catch (IOException e) {
                        System.out.println("[KLUDA] Nevarēja atjaunot CSV failu no pagaidu faila");
                        e.printStackTrace();
                    }
                }
                break;
            case 4: // Rediģēt lietotāja datus
                System.out.println("Rediģēt lietotāja datus");
                System.out.print("Ievadiet Personas kodu, kuru vēlaties rediģēt: ");
                String editPcode = scanner.nextLine().trim();

                File csvFile4 = new File(csvPath);
                if (!csvFile4.exists()) {
                    System.out.println("[KLUDA] CSV fails nav atrasts: " + csvFile4.getAbsolutePath());
                    break;
                }

                List<String> lines = new ArrayList<>();
                try (BufferedReader br = new BufferedReader(new FileReader(csvFile4))) {
                    String l;
                    while ((l = br.readLine()) != null) lines.add(l);
                } catch (IOException e) {
                    System.out.println("[KLUDA] Nevar nolasīt CSV failu");
                    e.printStackTrace();
                    break;
                }

                int hitIndex = -1;
                String origName = null, origUzv = null, origEmail = null, origP = null;
                for (int i = 0; i < lines.size(); i++) {
                    String line = lines.get(i).trim();
                    if (line.isEmpty()) continue;
                    String[] p = line.split(",");
                    if (p.length >= 4) {
                        String pc = p[3].trim().replaceAll("^\"|\"$", "");
                        if (pc.equals(editPcode)) {
                            hitIndex = i;
                            origName = p[0].trim().replaceAll("^\"|\"$", "");
                            origUzv = p[1].trim().replaceAll("^\"|\"$", "");
                            origEmail = p[2].trim().replaceAll("^\"|\"$", "");
                            origP = pc;
                            break;
                        }
                    }
                }

                if (hitIndex == -1) {
                    System.out.println("[INFO] Ieraksts netika atrasts priekš personas koda: " + editPcode);
                    break;
                }

                // build existing email/pcode sets excluding the target record
                Set<String> existingEmailsE = new HashSet<>();
                Set<String> existingPcodesE = new HashSet<>();
                for (int i = 0; i < lines.size(); i++) {
                    if (i == hitIndex) continue;
                    String line = lines.get(i).trim();
                    if (line.isEmpty()) continue;
                    String[] p = line.split(",");
                    if (p.length >= 3) existingEmailsE.add(p[2].trim().toLowerCase());
                    if (p.length >= 4) existingPcodesE.add(p[3].trim());
                }

                System.out.println("Pašreizējie dati:\n vārds: " + origName + "\n uzvards: " + origUzv + "\n epasts: " + origEmail + "\n personasKods: " + origP);
                System.out.println("Ievadiet jaunos datus (atstājiet tukšu, lai saglabātu esošo):");
                System.out.print("vārds:");
                String newName = scanner.nextLine().trim();
                if (newName.isEmpty()) newName = origName;
                System.out.print("uzvards:");
                String newUzv = scanner.nextLine().trim();
                if (newUzv.isEmpty()) newUzv = origUzv;
                System.out.print("epasts:");
                String newEmail = scanner.nextLine().trim();
                if (newEmail.isEmpty()) newEmail = origEmail;
                System.out.print("personasKods:");
                String newPcode = scanner.nextLine().trim();
                if (newPcode.isEmpty()) newPcode = origP;

                // basic validations (reuse same simple rules as registration)
                if (newName.length() >= 15 || newName.length() <= 3) {
                    System.out.println("[KLUDA] Nepareizi ievadits vārda simbolu garums.");
                    break;
                }
                if (newUzv.length() >= 20 || newUzv.length() <= 3) {
                    System.out.println("[KLUDA] Nepareizi ievadits uzvārda simbolu garums.");
                    break;
                }
                if (!(newEmail.contains("@gmail.com") || newEmail.contains("@inbox.com"))) {
                    System.out.println("[KLUDA] Nepareizi ievadits epasts.");
                    break;
                }
                if (!newPcode.contains("-") || newPcode.length() < 12) {
                    System.out.println("[KLUDA] Nepareizi ievadits personas kods.");
                    break;
                }

                if (existingEmailsE.contains(newEmail.toLowerCase())) {
                    System.out.println("[KLUDA] Epasts jau aizņemts: " + newEmail);
                    break;
                }
                if (existingPcodesE.contains(newPcode)) {
                    System.out.println("[KLUDA] Personas kods jau aizņemts: " + newPcode);
                    break;
                }

                // perform backup then write updated file
                File backupE = new File(csvPath + ".bak");
                try {
                    Files.copy(csvFile4.toPath(), backupE.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    System.out.println("[KLUDA] Nevar izveidot backup failu: " + backupE.getAbsolutePath());
                    e.printStackTrace();
                    break;
                }

                File tempE = new File(csvPath + ".tmp");
                try (FileWriter fw = new FileWriter(tempE)) {
                    for (int i = 0; i < lines.size(); i++) {
                        if (i == hitIndex) {
                            String updated = newName + "," + newUzv + "," + newEmail + "," + newPcode;
                            fw.write(updated + System.lineSeparator());
                        } else {
                            fw.write(lines.get(i) + System.lineSeparator());
                        }
                    }
                } catch (IOException e) {
                    System.out.println("[KLUDA] Kļūda rakstot pagaidu failu");
                    e.printStackTrace();
                    break;
                }

                try {
                    Files.move(tempE.toPath(), csvFile4.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("[INFO] Ieraksts veiksmīgi atjaunināts.");
                    System.out.println("Backup saglabāts: " + backupE.getAbsolutePath());
                } catch (IOException e) {
                    System.out.println("[KLUDA] Neizdevās atjaunināt CSV failu");
                    e.printStackTrace();
                }
                break;
            case 5:
                System.out.println("Programma tiek apturēta.");
                scanner.close();
                return;
            
        
            default:
                break;
        }

    }
}

class Student{
    private String name;
    private String uzvards;
    private String epasts;
    private String personasKods;
    
    Student(String name, String uzvards, String epasts, String personasKods){
        this.name = name;
        this.uzvards = uzvards;
        this.epasts = epasts;
        this.personasKods = personasKods;
    }

    public String getName() { return name;}
    public String getUzvards() {return uzvards;}
    public String getEpasts() {return epasts;}
    public String getPersonasKods() {return personasKods;}
}