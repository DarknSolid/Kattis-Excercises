import java.util.*;

public class Simplicity {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {

            while (sc.hasNext()) {
                calcResult(sc.nextLine());
            }
        }
    }

    private static void calcResult(String word) {
        HashMap<Character, Integer> amountOf = new HashMap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!amountOf.containsKey(c)) amountOf.put(c, 0);
            amountOf.put(c, amountOf.get(c) + 1);
        }

        for (Character c : amountOf.keySet()) pq.add(amountOf.get(c));

        int removedLetters = 0;
        while (!pq.isEmpty() && pq.size() > 2) {
            int amount = pq.remove();
            removedLetters += amount;
        }
        System.out.println(removedLetters);
    }
}
