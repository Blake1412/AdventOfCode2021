import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day12 {
    Map<String, List<String>> paths = new HashMap<>();

    public Day12() {
        try {
            Scanner scan = new Scanner(new File("data.txt"));
            while (scan.hasNext()) {
                String[] caves = scan.nextLine()
                                     .split("-");
                String caveOne = caves[0], caveTwo = caves[1];
                if (paths.containsKey(caveOne)) paths.get(caveOne)
                                                     .add(caveTwo);
                else paths.put(caveOne, new ArrayList<>(List.of(caveTwo)));

                if (paths.containsKey(caveTwo)) paths.get(caveTwo)
                                                     .add(caveOne);
                else paths.put(caveTwo, new ArrayList<>(List.of(caveOne)));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int part1() {
        return numberOfPaths("start", new HashSet<>(), false);
    }

    public int part2() {
        return numberOfPaths("start", new HashSet<>(), true);
    }

    private int numberOfPaths(String start, Set<String> path, boolean visitTwice) {
        int count = 0;
        for (String neighbour : paths.get(start)) {
            Set<String> newPath = new HashSet<>(path);
            if (neighbour.equals("start")) continue;
            if (neighbour.equals("end")) {
                count++;
                continue;
            }
            if (neighbour.toLowerCase()
                         .equals(neighbour)) {
                if (newPath.add(neighbour)) {
                    count += numberOfPaths(neighbour, newPath, visitTwice);
                } else if (visitTwice) {
                    count += numberOfPaths(neighbour, newPath, false);
                }
            } else count += numberOfPaths(neighbour, newPath, visitTwice);
        }
        return count;
    }
}
