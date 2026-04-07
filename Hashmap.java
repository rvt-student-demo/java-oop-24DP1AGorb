import java.util.HashMap;
class Hashmap{
    public static void main(String[] args) {
        HashMap<String, Integer> salaries = new HashMap<>();
        salaries.put("John", 2000);
        salaries.put("Sally", 2000);

        System.out.println(salaries.get("John"));

    }
}