import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day05 {

    int[][] map;
    List<Integer> x1Vertices = new ArrayList<>(), y1Vertices = new ArrayList<>(), x2Vertices = new ArrayList<>(), y2Vertices = new ArrayList<>();

    public Day05() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("data.txt"));
        while (scan.hasNext()) {
            String[] st = scan.nextLine().split(" -> ");
            x1Vertices.add(Integer.parseInt(st[0].split(",")[0]));
            y1Vertices.add(Integer.parseInt(st[0].split(",")[1]));
            x2Vertices.add(Integer.parseInt(st[1].split(",")[0]));
            y2Vertices.add(Integer.parseInt(st[1].split(",")[1]));
        }

        int mapMax = Math.max(Math.max(x1Vertices.stream().reduce(Integer::max).orElseThrow(), x2Vertices.stream().reduce(Integer::max).orElseThrow()),
                Math.max(y1Vertices.stream().reduce(Integer::max).orElseThrow(), y2Vertices.stream().reduce(Integer::max).orElseThrow()));

        map = new int[mapMax + 1][mapMax + 1];
    }

    public int part1() {
        for (int i = 0; i < x1Vertices.size(); i++) {
            int x1 = x1Vertices.get(i), y1 = y1Vertices.get(i), x2 = x2Vertices.get(i), y2 = y2Vertices.get(i);

            if (x1 == x2) {
                for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                    map[x1][y]++;
                }
            } else if (y1 == y2) {
                for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                    map[x][y1]++;
                }
            }
        }
        return mapCount();
    }

    public int part2() {
        for (int i = 0; i < x1Vertices.size(); i++) {
            int x1 = x1Vertices.get(i), y1 = y1Vertices.get(i), x2 = x2Vertices.get(i), y2 = y2Vertices.get(i);
            int dx = 0, dy = 0;

            if (x2 > x1) dx = 1;
            else if (x2 < x1) dx = -1;

            if (y2 > y1) dy = 1;
            else if (y2 < y1) dy = -1;

            map[x1][y1]++;
            while (x1 != x2 || y1 != y2) {
                x1 += dx;
                y1 += dy;
                map[x1][y1]++;
            }
        }
        return mapCount();
    }

    private int mapCount() {
        int count = 0;

        for (int[] x : map) {
            for (int y = 0; y < map.length; y++) {
                if (x[y] > 1) {
                    count++;
                }
            }
        }
        return count;
    }
}
