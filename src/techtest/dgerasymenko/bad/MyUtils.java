package techtest.dgerasymenko.bad;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class MyUtils {
    private static final String JOIN_DELIMITER = "\n";
    private static final String SPLIT_DELIMITER = "\\P{L}+";

    public static String readDataFromConsole() {
        Scanner scanner = new Scanner(System.in);
        StringJoiner stringJoiner = new StringJoiner(JOIN_DELIMITER);

        System.out.println("Please enter a text:");
        do {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    break;
                }
                stringJoiner.add(line);
            }

            if (stringJoiner.toString().isBlank()) {
                System.out.println("The entered text can not be blank. Please try again:");
            }
        } while (stringJoiner.toString().isBlank());

        scanner.close();
        return stringJoiner.toString();
    }

    public static void printResults(String inputData) {
        String interimString = Arrays.stream(inputData.split(SPLIT_DELIMITER))
                .map(MyUtils::findFirstUniqueCharacter)
                .collect(Collectors.joining());

        if (interimString.isEmpty()) {
            System.out.println("There are no unique characters");
        } else {
            String character = findFirstUniqueCharacter(interimString);
            System.out.println("The intermediate string (all first unique characters among entered words): " + interimString);
            System.out.println(character.isEmpty()
                    ? "There are no unique characters in the intermediate string"
                    : "The first unique character: " + character);
        }
    }

    private static String findFirstUniqueCharacter(String string) {
        if (string == null) {
            throw new IllegalArgumentException("the provided string can not be null");
        }

        for (int i = 0; i < string.length(); i++) {
            if (isUniqueCharacter(string.charAt(i), string.toCharArray())) {
                return String.valueOf(string.charAt(i));
            }
        }
        return "";
    }

    private static boolean isUniqueCharacter(char symbol, char[] chars) {
        int count = 0;
        for (char ch : chars) {
            count += ch == symbol ? 1 : 0;
            if (count > 1) {
                return false;
            }
        }
        return true;
    }
}
