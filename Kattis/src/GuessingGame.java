import java.util.Scanner;

public class GuessingGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            determineLog(sc);
        }
    }

    private static void determineLog(Scanner sc) {
        boolean guessedTooHigh;
        int currentGuess = -1;
        int min = 1, max = 10;
        String s = sc.nextLine();
        String answer = "Stan may be honest";
        String wrongAnswer = "Stan is dishonest";

        while (s.compareTo("right on") != 0) {
            currentGuess = Integer.parseInt(s);
            if (currentGuess == 0) return;

            s = sc.nextLine();
            if (s.compareTo("right on") == 0) {
                break;
            }
            guessedTooHigh = isLowerString(s);

            if (guessedTooHigh && currentGuess <= max) {
                max = currentGuess - 1;
            } else if (!guessedTooHigh && currentGuess >= min) {
                min = currentGuess + 1;
            }
            if (max < min) answer = wrongAnswer;

//            System.out.println("min and max: " + min + " & " + max);
            s = sc.nextLine();

        }
        if (currentGuess < min || currentGuess > max) answer = wrongAnswer;
        System.out.println(answer);
    }

    private static boolean isLowerString(String s) {
        return s.compareTo("too high") == 0;
    }
}
