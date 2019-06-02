import java.util.Scanner;

public class Pot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int addends = Integer.parseInt(sc.nextLine());
        int sum = 0;
        for (int i = 0; i < addends; i++) {
            String number = sc.nextLine();
            int base = Integer.parseInt(number.substring(0, number.length()-1));
            int top = Character.getNumericValue(number.charAt(number.length()-1));
            sum += Math.pow(base,top);
        }
        System.out.println(sum);
    }
}
