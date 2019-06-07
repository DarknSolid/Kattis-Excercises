import java.util.HashSet;
import java.util.Scanner;

public class Beekeeper {

    private static HashSet<Character> wovels = new HashSet<>();

    static {
        wovels.add('a');
        wovels.add('i');
        wovels.add('e');
        wovels.add('o');
        wovels.add('u');
        wovels.add('y');
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            int words = sc.nextInt();
            if (words == 0) return;
            sc.nextLine();
            System.out.println(findWordWithMostAffinity(sc, words));
        }
    }

    private static String findWordWithMostAffinity(Scanner sc, int words) {
        int bestAffinity = -1;
        String bestWord = "NONE";

        for (int i = 0; i < words; i++) {

            String currentWord = sc.nextLine();
            int currentAffinity = findWordsAffinity(currentWord);

            if (currentAffinity > bestAffinity) {
                bestAffinity = currentAffinity;
                bestWord = currentWord;
            }
        }

        return bestWord;
    }

    private static int findWordsAffinity(String currentWord) {
        char currentWovel;
        int affinity = 0;
        for (int i = 0; i < currentWord.length(); i++) {
            char c = currentWord.charAt(i);
            if (wovels.contains(c)) currentWovel = c;
            else continue;
            try {
                if (currentWord.charAt(i + 1) == currentWovel) {
                    affinity++;
                    i++;
                }
            } catch (IndexOutOfBoundsException e) {continue; }
        }
        return affinity;
    }
}
