import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StickySituation {

    static long minRange, maxRange;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Long> sticks = new ArrayList<>();
        int toScan = sc.nextInt();
        for (int i = 0; i < toScan; i++) {
            sticks.add(sc.nextLong());
        }
        Collections.sort(sticks);
        System.out.println(canMakeTriangle(sticks));
    }

    private static String canMakeTriangle(ArrayList<Long> sticks) {
        for (int i = 0; i < sticks.size() - 2; i++) {
            for (int j = i + 1; j < sticks.size() - 1; j++) {
                if (isMatch(sticks, i, j)) return "possible";
            }
        }
        return "impossible";
    }

    private static boolean isMatch(ArrayList<Long> sticks, int i, int j) {
        long valI = sticks.get(i);
        long valJ = sticks.get(j);
        maxRange = valI + valJ;
        minRange = valJ - valI;
        if (valI - valJ > minRange) minRange = valI - valJ;
//        System.out.println("minRange:" + minRange + " maxRange:" + maxRange);
//        System.out.println(valI + " " + valJ);
        if (minRange >= maxRange) return false;

        return findMatchingNumber(sticks, j + 1, sticks.size() - 1);
    }

    private static boolean findMatchingNumber(ArrayList<Long> sticks, int min, int max) {
        int mid = Math.floorDiv(max + min, 2);
        if (sticks.get(mid) < maxRange && sticks.get(mid) > minRange) return true;
        if (min == max) return false;
        if (sticks.get(mid) >= maxRange) return findMatchingNumber(sticks, min, mid);
        return findMatchingNumber(sticks, mid, max);
    }
}
