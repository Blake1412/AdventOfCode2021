import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day03 {
    List<String> numbers = new ArrayList<>();

    public Day03() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("data.txt"));
        while (scan.hasNextLine()) {
            numbers.add(scan.nextLine());
        }
    }

    private char frequency(int index, String HighestLowest, List<String> numbers) {
        List<Character> digits = new ArrayList<>();

        for (String number : numbers) {
            digits.add(number.charAt(index));
        }

        if (HighestLowest.equals("Highest")) {
            if (Collections.frequency(digits, '1') >= Collections.frequency(digits, '0')) {
                return '1';
            } else {
                return '0';
            }
        } else {
            if (Collections.frequency(digits, '1') >= Collections.frequency(digits, '0')) {
                return '0';
            } else {
                return '1';
            }
        }
    }

    private int rating(String HighestLowest) {
        List<String> numbersCopy = new ArrayList<>(numbers);

        for (int i = 0; i < numbers.get(0).length(); i++) {
            char mostLeastFrequent = frequency(i, HighestLowest, numbersCopy);

            for (int j = 0; j < numbersCopy.size(); j++) {
                if (numbersCopy.get(j).charAt(i) != mostLeastFrequent) {
                    numbersCopy.remove(j);
                    j--;
                }
            }
            if (numbersCopy.size() == 1) {
                break;
            }
        }
        return (int) Long.parseLong(numbersCopy.get(0), 2);
    }

    public int part1() {
        StringBuilder gammaRate = new StringBuilder(), epsilonRate = new StringBuilder();

        for (int i = 0; i < numbers.get(0).length(); i++) {
            gammaRate.append(frequency(i, "Highest", numbers));
            if (gammaRate.charAt(i) == '1') {
                epsilonRate.append('0');
            } else {
                epsilonRate.append('1');
            }
        }
        return (int) (Long.parseLong(gammaRate.toString(), 2) * Long.parseLong(epsilonRate.toString(), 2));
    }

    public int part2() {
        return rating("Highest") * rating("Lowest");
    }
}
