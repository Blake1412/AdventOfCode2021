import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Day10 {
    List<String> lines = new ArrayList<>();
    Map<Character, Character> characters = new HashMap<>(Map.of('(', ')', '[', ']', '{', '}', '<', '>'));

    public Day10() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("data.txt"));
        while (scan.hasNext()) {
            lines.add(scan.nextLine());
        }
    }

    private boolean isCorrupt(String line) {
        Stack<Character> stack = new Stack<>();
        for (char character : line.toCharArray()) {
            switch (character) {
                case '(', '[', '{', '<' -> stack.push(character);
                case ')', ']', '}', '>' -> {
                    if (characters.get(stack.pop()) != character) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int part1() {
        int score = 0;

        for (String line : lines) {
            Stack<Character> stack = new Stack<>();
            for (char character : line.toCharArray()) {
                int flag = 0;
                switch (character) {
                    case '(', '[', '{', '<' -> stack.push(character);
                    case ')', ']', '}', '>' -> {
                        if (characters.get(stack.pop()) != character) {
                            flag++;
                            switch (character) {
                                case ')' -> score += 3;
                                case ']' -> score += 57;
                                case '}' -> score += 1197;
                                case '>' -> score += 25137;
                            }
                        }
                    }
                }
                if (flag > 0) {
                    break;
                }
            }
        }
        return score;
    }

    public Long part2() {
        List<Long> scores = new ArrayList<>();
        for (String line : lines) {
            if (isCorrupt(line)) {
                continue;
            }
            Stack<Character> stack = new Stack<>();
            for (char character : line.toCharArray()) {
                switch (character) {
                    case '(', '[', '{', '<' -> stack.push(character);
                    case ')', ']', '}', '>' -> stack.pop();
                }
            }
            long score = 0;
            while (!stack.isEmpty()) {
                char character = stack.pop();
                score *= 5;
                switch (character) {
                    case '(' -> score += 1;
                    case '[' -> score += 2;
                    case '{' -> score += 3;
                    case '<' -> score += 4;
                }
            }
            scores.add(score);
        }
        scores.sort(Comparator.naturalOrder());
        return scores.get((scores.size() / 2));
    }
}
