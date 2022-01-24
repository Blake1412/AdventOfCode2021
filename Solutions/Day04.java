import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day04 {
    List<List<Map<Integer, Boolean>>> allBoards = new ArrayList<>();
    List<Integer> drawNumbers = new ArrayList<>();

    public Day04() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("data.txt"));
        List<String> raw = new ArrayList<>(Arrays.asList(scan.useDelimiter("\\Z").next().split("\r\n\r\n")));
        for (String number : raw.get(0).split(",")) {
            drawNumbers.add(Integer.parseInt(number));
        }
        raw.remove(0);
        for (String board : raw) {
            List<Map<Integer, Boolean>> boards = new ArrayList<>();
            for (String row : board.split("\r\n")) {
                Map<Integer, Boolean> rows = new LinkedHashMap<>();
                List<String> numbers = new ArrayList<>(Arrays.asList(row.split(" ")));
                numbers.removeIf(x -> x.equals(""));
                for (String number : numbers) {
                    rows.put(Integer.parseInt(number), false);
                }
                boards.add(rows);
                allBoards.add(boards);
            }
        }
    }

    private void markBoard(List<Map<Integer, Boolean>> board, int drawNumber) {
        for (Map<Integer, Boolean> row : board) {
            if (row.containsKey(drawNumber)) {
                row.replace(drawNumber, true);
            }
        }
    }

    private boolean isWinner(List<Map<Integer, Boolean>> board) {
        List<Map<Integer, Boolean>> columns = new ArrayList<>();

        for (int i = 0; i < board.get(0).size(); i++) {
            Map<Integer, Boolean> column = new LinkedHashMap<>();
            for (int j = 0; j < board.size(); j++) {
                List<Integer> numbers = new ArrayList<>(board.get(j).keySet());
                column.put(numbers.get(i), board.get(j).get(numbers.get(i)));
            }
            columns.add(column);
        }

        for (Map<Integer, Boolean> column : columns) {
            for (Integer number : column.keySet()) {
                if (!column.containsValue(false)) {
                    return true;
                } else {
                    break;
                }
            }
        }

        for (Map<Integer, Boolean> row : board) {
            for (Integer number : row.keySet()) {
                if (!row.containsValue(false)) {
                    return true;
                } else {
                    break;
                }
            }
        }
        return false;
    }

    private int sum(List<Map<Integer, Boolean>> board) {
        int sum = 0;

        for (Map<Integer, Boolean> row : board) {
            for (Integer number : row.keySet()) {
                if (!row.get(number)) {
                    sum += number;
                }
            }
        }
        return sum;
    }

    public int part1() {
        int score = 0;
        for (int drawNumber : drawNumbers) {
            for (List<Map<Integer, Boolean>> board : allBoards) {
                markBoard(board, drawNumber);
                if (isWinner(board)) {
                    score = sum(board) * drawNumber;
                    return score;
                }
            }
        }
        return score;
    }

    public int part2() {
        int score = 0;
        for (int drawNumber : drawNumbers) {
            for (int board = 0; board < allBoards.size(); board++) {
                markBoard(allBoards.get(board), drawNumber);
                if (isWinner(allBoards.get(board)) && allBoards.size() > 1) {
                    allBoards.remove(board);
                    board--;
                } else if (isWinner(allBoards.get(board))) {
                    score = sum(allBoards.get(board)) * drawNumber;
                    return score;
                }
            }
        }
        return score;
    }


    public void test() {
        System.out.println(isWinner(allBoards.get(0)));
    }
}
