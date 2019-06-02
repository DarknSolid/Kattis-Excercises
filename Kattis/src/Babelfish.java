import java.util.HashMap;
import java.util.Scanner;

public class Babelfish {
    public static void main(String[] args) {
        HashMap<String, String> dictionary = new HashMap<>();
        Scanner sc = new Scanner(System.in);

        //Populate dictionary:
        String line = sc.nextLine();
        while (line.compareTo("") != 0) {
            String[] words = line.split(" ");
            dictionary.put(words[1], words[0]);
            line = sc.nextLine();
        }

        //Read alien language:
        while (sc.hasNext()) {
            String word = sc.nextLine();
            if (dictionary.containsKey(word)) {
                System.out.println(dictionary.get(word));
            } else System.out.println("eh");
        }

    }
}
