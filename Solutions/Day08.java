import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day08 {
    List<List<String>> signalPatterns = new ArrayList<>(), outputValues = new ArrayList<>();

    public Day08() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("data.txt"));
        while (scan.hasNext()) {
            String[] st = scan.nextLine().split(" [|] ");
            signalPatterns.add(Arrays.asList(st[0].split(" ")));
            outputValues.add(Arrays.asList(st[1].split(" ")));
        }
        for (List<String> signalPattern : signalPatterns) {
            signalPattern.sort(Comparator.comparing(String::length));
        }
    }

    public int part1() {
        int count = 0;

        for (List<String> outputValue : outputValues) {
            for (String value : outputValue) {
                int length = value.length();
                if (length == 2 || length == 3 || length == 4 || length == 7)
                    count++;
            }
        }
        return count;
    }

    public int part2() {
        int totalOutput = 0;

        int[][] digits = {{1, 1, 1, 0, 1, 1, 1}, {0, 0, 1, 0, 0, 1, 0}, {1, 0, 1, 1, 1, 0, 1},
                {1, 0, 1, 1, 0, 1, 1}, {0, 1, 1, 1, 0, 1, 0}, {1, 1, 0, 1, 0, 1, 1}, {1, 1, 0, 1, 1, 1, 1},
                {1, 0, 1, 0, 0, 1, 0}, {1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 0, 1, 1}};

        char[] segments = new char[7];

        for (int k = 0; k < signalPatterns.size(); k++) {
            StringBuilder output = new StringBuilder();
            List<String> signalPattern = signalPatterns.get(k);

            String signalOne = signalPattern.get(0), signalTwo = "", signalThree = "",
                    signalFour = signalPattern.get(2), signalFive = "", signalSeven = signalPattern.get(1);

            for (int i = 0; i < 3; i++) {
                if (signalOne.indexOf(signalSeven.charAt(i)) == -1) {
                    segments[0] = signalSeven.charAt(i);

                    if (i == 1) {
                        segments[2] = signalSeven.charAt(2);
                        segments[5] = signalSeven.charAt(0);
                    } else {
                        segments[2] = signalSeven.charAt(Math.abs(i - 1));
                        segments[5] = signalSeven.charAt(Math.abs(i - 2));
                    }
                }
            }

            for (int i = 3; i < 6; i++) {
                if (signalPattern.get(i).indexOf(segments[2]) != -1 && signalPattern.get(i).indexOf(segments[5]) != -1) {
                    signalThree = signalPattern.get(i);
                }
            }

            for (int i = 0; i < 4; i++) {
                if (signalThree.indexOf(signalFour.charAt(i)) == -1) {
                    segments[1] = signalFour.charAt(i);
                } else if (signalFour.charAt(i) != segments[2] && signalFour.charAt(i) != segments[5]) {
                    segments[3] = signalFour.charAt(i);
                }
            }

            for (int i = 3; i < 6; i++) {
                if (!signalPattern.get(i).equals(signalThree)) {
                    if (signalPattern.get(i).indexOf(segments[1]) != -1) {
                        signalFive = signalPattern.get(i);
                    } else {
                        signalTwo = signalPattern.get(i);
                    }
                }
            }

            if (signalFive.indexOf(segments[2]) != -1) {
                char temp = segments[5];
                segments[5] = segments[2];
                segments[2] = temp;
            }

            for (int i = 0; i < 5; i++) {
                if (signalFour.indexOf(signalFive.charAt(i)) == -1 && signalFive.charAt(i) != segments[0]) {
                    segments[6] = signalFive.charAt(i);
                }

                if (signalFive.indexOf(signalTwo.charAt(i)) == -1 && signalTwo.charAt(i) != segments[2]) {
                    segments[4] = signalTwo.charAt(i);
                }
            }

            List<String> outputValue = outputValues.get(k);

            for (String value : outputValue) {
                int[] outputDigit = new int[7];
                for (int i = 0; i < value.length(); i++) {
                    for (int j = 0; j < segments.length; j++) {
                        if (value.charAt(i) == segments[j]) {
                            outputDigit[j]++;
                        }
                    }
                }

                for (int i = 0; i < digits.length; i++) {
                    if (Arrays.equals(outputDigit, digits[i])) {
                        output.append(i);
                    }
                }
            }
            totalOutput += Integer.parseInt(output.toString());
        }
        return totalOutput;
    }
}
