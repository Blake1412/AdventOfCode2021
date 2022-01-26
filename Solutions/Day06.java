import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day06 {
    long[] fish = new long[9];

    public Day06() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("data.txt"));
        while (scan.hasNext()) {
            int startDay = scan.useDelimiter("\\D").nextInt();
            fish[startDay]++;
        }
    }

    private long fishAmount(int days) {
        long fishTotal = 0;

        for (int day = 0; day < days; day++) {
            long temp = fish[0];
            for (int i = 0; i < 9; i++) {
                switch (i) {
                    case 8 -> fish[i] = temp;
                    case 6 -> fish[i] = temp + fish[7];
                    default -> fish[i] = fish[i + 1];
                }
            }
        }
        for (long fish : fish) {
            fishTotal += fish;
        }
        return fishTotal;
    }

    public long part1() {
        return fishAmount(80);
    }

    public long part2() {
        return fishAmount(256);
    }
}
