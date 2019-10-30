import java.util.Scanner;

public class ThreeDPrintedStatues {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int printAmount = sc.nextInt();

        int days = 0;
        int printers = 1;

        while (printers < printAmount) {
            printers *= 2;
            days++;
        }
        //one extra day to print
        days++;

        System.out.println(days);
    }
}
