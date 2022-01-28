import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day07 {
    List<Integer> positions;
    int median, mean, fuel;

    public Day07() throws FileNotFoundException {
        positions = new ArrayList<>();
        fuel = 0;

        Scanner scan = new Scanner(new File("data.txt"));
        String[] st = scan.nextLine().split(",");
        for (String s : st) {
            positions.add(Integer.parseInt(s));
        }
        Collections.sort(positions);
        median = positions.size() / 2 - 1;
        for (int i : positions) {
            mean += i;
        }
        mean /= positions.size();
    }

    public int part1() {
        for (Integer position : positions) {
            fuel += Math.abs(position - (positions.get(median)));
        }
        return fuel;
    }

    public int part2() {
        for (Integer position : positions) {
            int n = Math.abs(position - mean);
            fuel += (n * (n + 1)) / 2;
        }
        return fuel;
    }
}