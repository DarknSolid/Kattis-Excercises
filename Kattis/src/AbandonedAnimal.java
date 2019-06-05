import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class AbandonedAnimal {

    private static int firstShop = -1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<HashSet<String>> shops = new ArrayList<>();
        ArrayList<String> inventory = new ArrayList<>();
        int amountOfShops = sc.nextInt();
        int items = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < amountOfShops; i++) {
            shops.add(new HashSet<>());
        }

        for (int i = 0; i < items; i++) {
            String[] info = sc.nextLine().split(" ");
            shops.get(Integer.parseInt(info[0])).add(info[1]);
        }

        int itemsInInventory = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < itemsInInventory; i++) {
            inventory.add(sc.nextLine());
        }

        if (isPossibleRoute(shops, inventory)) {
            if (isPossibleRoute(shops, inventory)) {
                System.out.println("ambiguous");
            } else System.out.println("unique");
        } else System.out.println("impossible");
    }

    private static boolean isPossibleRoute(ArrayList<HashSet<String>> shops, ArrayList<String> inventory) {
        int shopNumber = 0;
        if (firstShop != -1) shopNumber = firstShop + 1;

        if (shopNumber >= shops.size()) return false;

        int foundItems = 0;
        boolean didFindFirstShop = false;

        shopIterator:
        for (String item : inventory) {
            while (!shops.get(shopNumber).contains(item)) {
                shopNumber++;
                if (shopNumber == shops.size()) break shopIterator;
            }
            if (!didFindFirstShop) {
                didFindFirstShop = true;
                firstShop = shopNumber;
            }
            foundItems++;
        }

        return (foundItems == inventory.size());
    }
}
