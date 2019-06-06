import java.util.Scanner;

public class TrollHunt {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int remainingBridges = sc.nextInt() - 1;
        int knights = sc.nextInt();
        int groupSize = sc.nextInt();

        int groups = Math.floorDiv(knights, groupSize);
        double days = Math.ceil((double)remainingBridges/groups);

        System.out.printf("%.0f", days);

    }
}
