import java.util.Scanner;

public class DeathKnight {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int amountOfBattles = Integer.parseInt(sc.nextLine());
        int lostBattles = 0;

        for (int i = 0; i < amountOfBattles; i++) {
            String log = sc.nextLine();

            for (int j = 0; j < log.length(); j++) {
                try {
                    if (log.charAt(j) == 'C' && log.charAt(j + 1) == 'D') {
                        lostBattles++;
                        break;
                    }
                } catch (IndexOutOfBoundsException e) {
                    break;

                }
            }
        }
        System.out.println(amountOfBattles - lostBattles);
    }
}
