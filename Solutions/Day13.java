import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day13 {
    List<String> dots = new ArrayList<>(), instructions = new ArrayList<>();
    char[][] paper;

    public Day13() {
        try {
            Scanner scan = new Scanner(new File("data.txt"));
            String data;
            while (scan.hasNext()) {
                data = scan.nextLine();
                if (data.equals("")) {
                    break;
                }
                dots.add(data);
            }
            while (scan.hasNext()) {
                data = scan.nextLine();
                instructions.add(data.split("along ")[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int maxX = 0, maxY = 0;

        for (String dot : dots) {
            int y = Integer.parseInt(dot.split(",")[0]);
            int x = Integer.parseInt(dot.split(",")[1]);

            maxY = Math.max(maxY, y);
            maxX = Math.max(maxX, x);
        }

        paper = new char[maxX + 1][maxY + 1];
        for (char[] row : paper) {
            Arrays.fill(row, '.');
        }

        dots.forEach(row -> {
            int x = Integer.parseInt(row.split(",")[0]);
            int y = Integer.parseInt(row.split(",")[1]);
            paper[y][x] = '#';
        });

    }

    public int part1() {
        paper = fold(paper, instructions.get(0));
        int dots = 0;
        for (char[] row : paper) {
            for (char ch : row) {
                if (ch == '#') dots++;
            }
        }
        return dots;
    }

    public void part2() {
        for (String instruction : instructions) {
            paper = fold(paper, instruction);
        }
        Arrays.stream(paper)
              .forEach(System.out::println);
    }

    public char[][] fold(char[][] paper, String instruction) {
        int foldLine = Integer.parseInt(instruction.split("=")[1]);
        if (instruction.charAt(0) == 'x') {
            char[][] newPaper = new char[paper.length][foldLine];
            for (char[] row : paper) {
                for (int i = foldLine + 1; i < row.length; i++) {
                    if (row[i] == '#') row[(row.length - 1 - i)] = '#';
                }
            }
            for (int i = 0; i < paper.length; i++) {
                System.arraycopy(paper[i], 0, newPaper[i], 0, foldLine);
            }
            return newPaper;
        } else {
            for (int i = foldLine + 1; i < paper.length; i++) {
                for (int j = 0; j < paper[0].length; j++) {
                    if (paper[i][j] == '#') paper[paper.length - 1 - i][j] = '#';
                }
            }
            return Arrays.copyOfRange(paper, 0, foldLine);
        }
    }
}
