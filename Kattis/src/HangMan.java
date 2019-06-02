import java.util.HashSet;
import java.util.Scanner;

public class HangMan {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String word = sc.nextLine();
        String sequence = sc.nextLine();

        HashSet<Character> charsToGuess = new HashSet<>();
        for (int i = 0; i < word.length(); i++) {
            charsToGuess.add(word.charAt(i));
        }

        int lifes = 10;
        int correct = 0;

        for (int i = 0; i < sequence.length(); i++) {

            if (charsToGuess.contains(sequence.charAt(i)))
            {
                correct++;
                if (correct == charsToGuess.size()) {
                    System.out.println("WIN");
                    break;
                }
            } else {
                lifes--;
                if (lifes == 0) {
                    System.out.println("LOSE");
                    break;
                }
            }

        }
    }
}
