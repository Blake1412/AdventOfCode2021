import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day01 {
    ArrayList<Integer> measurements = new ArrayList<>();

    public Day01() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("data.txt"));
        while (scan.hasNext()) {
            measurements.add(scan.nextInt());
        }
    }

    public int part1() {
        int increased = 0;
        for (int i = 1; i < measurements.size(); i++) {
            if (measurements.get(i) > measurements.get(i - 1)) {
                increased++;
            }
        }
        return increased;
    }

    public int part2() {
        int increased = 0;
        for (int i = 0; i < measurements.size() - 3; i++) {
            int windowOne = measurements.get(i) + measurements.get(i + 1) + measurements.get(i + 2);
            int windowTwo = measurements.get(i + 1) + measurements.get(i + 2) + measurements.get(i + 3);
            if (windowTwo > windowOne) {
                increased++;
            }
        }
        return increased;
    }
}
