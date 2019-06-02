import java.util.Scanner;

public class AliceIntTheDigitalWorld {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int amountOfDatasets = sc.nextInt();
        int dataSize = sc.nextInt();
        int minVal = sc.nextInt();

        int[][] datasets = new int[amountOfDatasets][dataSize];

        //Fill datasets:
        for (int i = 0; i < datasets.length; i++) {
//            System.out.print("Dataset " + (i + 1) + ": ");
            for (int j = 0; j < dataSize; j++) {
                datasets[i][j] = sc.nextInt();
//                System.out.print(datasets[i][j] + " ");
            }
//            System.out.println();
        }

        //Find largest sums:
        for (int curDataSet = 0; curDataSet < amountOfDatasets; curDataSet++) {

            int sum = 0;
            int tempSum = 0;
            int indexOfMinVal = 0;
            boolean hasFoundMinVal = false;

            for (int i = 0; i < datasets[curDataSet].length; i++) {
                int value = datasets[curDataSet][i];
                //System.out.println("Value is: " + value);

                if (value == minVal) {
                    if (hasFoundMinVal) {
                        i = indexOfMinVal;
                        if (tempSum > sum) sum = tempSum;
                        tempSum = 0;
                        hasFoundMinVal = false;
                        continue;
                    } else {
                        hasFoundMinVal = true;
                        indexOfMinVal = i;
                    }
                }
                tempSum += value;
            }
            if (tempSum > sum) sum = tempSum;
            System.out.println(sum);
        }
    }
}
