import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day09 {
    List<List<Integer>> map = new ArrayList<>();
    List<Map<Integer, Boolean>> grid = new ArrayList<>();

    public Day09() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("data.txt"));
        while (scan.hasNext()) {
            String st = scan.nextLine();

            List<Integer> row = new ArrayList<>();
            for (int i = 0; i < st.length(); i++) {
                row.add(Character.getNumericValue(st.charAt(i)));
            }
            map.add(row);
        }

        for (List<Integer> row : map) {
            Map<Integer, Boolean> gridRow = new HashMap<>();
            for (int j = 0; j < row.size(); j++) {
                gridRow.put(j, false);
            }
            grid.add(gridRow);
        }
    }

    private boolean outOfBounds(int x, int y, int dx, int dy) {
        return (x + dx >= map.size() || x + dx < 0 || y + dy >= map.get(x).size() || y + dy < 0);
    }


    private boolean isLowPoint(int x, int y) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1 && dy != 0; dy += 2) {
                if (dx != 0) dy = 0;

                if (outOfBounds(x, y, dx, dy)) {
                    continue;
                }

                if (map.get(x + dx).get(y + dy) <= map.get(x).get(y)) {
                    return false;
                }
            }
        }
        return true;
    }

    private int basinSize(int x, int y) {
        int count = 0;

        if (map.get(x).get(y) == 9 || grid.get(x).get(y)) {
            return count;
        }

        grid.get(x).replace(y, true);
        count++;

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1 && dy != 0; dy += 2) {
                if (dx != 0) dy = 0;

                if (outOfBounds(x, y, dx, dy)) {
                    continue;
                }

                count += basinSize(x + dx, y + dy);
            }
        }
        return count;
    }

    public int part1() {
        int sum = 0;
        for (int x = 0; x < map.size(); x++) {
            for (int y = 0; y < map.get(x).size(); y++) {
                if (isLowPoint(x, y)) {
                    sum += map.get(x).get(y) + 1;
                }
            }
        }
        return sum;
    }

    public int part2() {
        List<Integer> basins = new ArrayList<>();

        for (int x = 0; x < map.size(); x++) {
            for (int y = 0; y < map.get(x).size(); y++) {
                if (isLowPoint(x, y)) {
                    basins.add(basinSize(x, y));
                }
            }
        }
        basins.sort(Comparator.reverseOrder());
        return basins.get(0) * basins.get(1) * basins.get(2);
    }
}
