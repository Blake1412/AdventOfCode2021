import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Day02 {
    List<Direction> directions = new ArrayList<>();
    List<Integer> magnitudes = new ArrayList<>();

    public Day02() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("data.txt"));
        while (scan.hasNext()) {
            String[] values = scan.nextLine().split(" ");
            directions.add(Direction.valueOf(values[0]));
            magnitudes.add(Integer.parseInt(values[1]));
        }
    }

    public int part1() {
        int horizontalPos = 0, depth = 0;

        for (int i = 0; i < directions.size(); i++) {
            Direction command = directions.get(i);
            int magnitude = magnitudes.get(i);

            switch (command) {
                case forward -> horizontalPos += magnitude;
                case down -> depth += magnitude;
                case up -> depth -= magnitude;
            }
        }
        return horizontalPos * depth;
    }

    public int part2() {
        int horizontalPos = 0, depth = 0, aim = 0;

        for (int i = 0; i < directions.size(); i++) {
            Direction command = directions.get(i);
            int magnitude = magnitudes.get(i);

            switch (command) {
                case down -> aim += magnitude;
                case up -> aim -= magnitude;
                case forward -> {
                    horizontalPos += magnitude;
                    depth += magnitude * aim;
                }
            }
        }
        return horizontalPos * depth;
    }

    enum Direction {
        up,
        down,
        forward
    }
}
