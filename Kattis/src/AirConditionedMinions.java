import java.util.*;

public class AirConditionedMinions {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rooms = Integer.parseInt(sc.nextLine());

        ArrayList<Minion> minions = new ArrayList<>();

        //Populate minions
        while(sc.hasNext()) {
            minions.add(new Minion(sc.nextInt(), sc.nextInt()));
        }
        Collections.sort(minions, new MinionComparator());

        Iterator<Minion> it = minions.iterator();
        Minion curMinion = it.next();
        int min = curMinion.getMin();
        int max = curMinion.getMax();
        while (it.hasNext()) {
            Minion nextMinion = it.next();

            if (max >= nextMinion.getMin()) {
                if (min < nextMinion.getMin()) min = nextMinion.getMin();
                if (max > nextMinion.getMax()) max = nextMinion.getMax();
                rooms--;
            } else {
                min = nextMinion.getMin();
                max = nextMinion.getMax();
            }
        }
        System.out.println(rooms);
    }
}

class Minion {
    private int min, max;

    public Minion(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}

class MinionComparator implements Comparator<Minion> {

    @Override
    public int compare(Minion m1, Minion m2) {
        if (m1.getMin() < m2.getMin()) return -1;
        if (m1.getMin() > m2.getMin()) return 1;
        return 0;
    }
}
