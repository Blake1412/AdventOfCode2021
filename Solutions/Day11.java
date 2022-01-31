import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day11 {
    int[][] grid = new int[10][10];

    public Day11() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("data.txt"));
        while (scan.hasNext()) {
            for (int[] row : grid) {
                String st = scan.nextLine();
                for (int y = 0; y < row.length; y++) {
                    row[y] = Character.getNumericValue(st.charAt(y));
                }
            }
        }
    }

    private void step() {
        for (int[] row : grid) {
            for (int y = 0; y < row.length; y++) {
                row[y]++;
            }
        }
    }

    private int flash(int x, int y) {
        int flashes = 1;
        grid[x][y] = 0;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) {
                    continue;
                }
                if (dx + x < 0 || dx + x >= grid.length || dy + y < 0 || dy + y >= grid[x].length) {
                    continue;
                }
                if (grid[dx + x][dy + y] == 0) {
                    continue;
                }
                grid[dx + x][dy + y]++;
                if (grid[dx + x][dy + y] > 9) {
                    flashes += flash(dx + x, dy + y);
                }
            }
        }
        return flashes;
    }

    private int getFlashes(int flashes) {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (grid[x][y] > 9 && grid[x][y] > 0) {
                    flashes += flash(x, y);
                }
            }
        }
        return flashes;
    }

    public int part1() {
        int flashes = 0;
        for (int i = 0; i < 100; i++) {
            step();
            flashes = getFlashes(flashes);
        }
        return flashes;
    }

    public int part2() {
        int flashes = 0;
        int step = 0;
        while (flashes != 100) {
            flashes = 0;
            step();
            step++;
            flashes = getFlashes(flashes);
        }
        return step;
    }
}
