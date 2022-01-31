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

    private int flash(int x, int y, boolean[][] flashed) {
        int flashes = 1;
        flashed[x][y] = true;
        grid[x][y] = 0;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) {
                    continue;
                }
                if (dx + x < 0 || dx + x >= grid.length || dy + y < 0 || dy + y >= grid[x].length) {
                    continue;
                }
                if (flashed[dx + x][dy + y]) {
                    continue;
                }
                grid[dx + x][dy + y]++;
                if (grid[dx + x][dy + y] > 9) {
                    flashes += flash(dx + x, dy + y, flashed);
                }
            }
        }
        return flashes;
    }

    private int getFlashes(int flashes, boolean[][] flashed) {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (grid[x][y] > 9 && !flashed[x][y]) {
                    flashes += flash(x, y, flashed);
                }
            }
        }
        return flashes;
    }

    public int part1() {
        int flashes = 0;
        for (int i = 0; i < 100; i++) {
            boolean[][] flashed = new boolean[10][10];
            step();
            flashes = getFlashes(flashes, flashed);
        }
        return flashes;
    }

    public int part2() {
        int flashes = 0;
        int step = 0;
        while (flashes != 100) {
            boolean[][] flashed = new boolean[10][10];
            flashes = 0;
            step();
            step++;
            flashes = getFlashes(flashes, flashed);
        }
        return step;
    }
}
