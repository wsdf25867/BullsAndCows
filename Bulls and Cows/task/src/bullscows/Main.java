package bullscows;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    static int n;
    static int m;

    static String secretCode;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the length of the secret code:");
        String stringN = sc.nextLine();
        if (validNumber(stringN)) {
            n = Integer.parseInt(stringN);
        } else {
            System.out.printf("Error: \"%s\" isn't a valid number.",stringN);
            return;
        }
        System.out.println("Input the number of possible symbols in the code:");
        String stringM = sc.nextLine();
        if (validNumber(stringM)) {
            m = Integer.parseInt(stringM);
        } else {
            System.out.printf("Error: \"%s\" isn't a valid number.",stringM);
        }

        if (m < n || n < 1) {
            System.out.println("Error: it's not possible to generate a code with a length of "+n+" with "+m+" unique symbols.");
            return;
        }
        if (n > 36) {
            System.out.println("Error: can't generate a secret number with a length " +
                    "of 11 because there aren't enough unique digits.");
            return;
        }
        if (m > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;
        }

        secretCode = generateCode();
        System.out.print("The secret is prepared: ");
        for (int i = 0; i < n; i++) {
            System.out.print("*");
        }
        if (m < 10) {
            System.out.println(" (0-9).");
        } else {
            System.out.println(" (0-9, a-"+(char)(86+m)+").");
        }
        System.out.println("okay, let's start a game!");
        int i = 1;
        while (true) {
            System.out.printf("Turn %d:\n", i);
            String s = sc.nextLine();
            boolean isSuccess = check(s);
            if (isSuccess) {
                System.out.printf("Grade: %d bulls\n", n);
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }
            i++;
        }
    }

    private static boolean validNumber(String s) {
        Pattern pattern = Pattern.compile("[^0-9]");
        return !pattern.matcher(s).find();
    }

    private static String generateCode() {
        String[] pool = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
                "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        Random random = new Random(m);
        String result = "";
        while (result.length() != n) {
            result+=pool[random.nextInt(m)];
            result = Arrays.stream(result.split("")).distinct().collect(Collectors.joining());
        }
        return result;
    }

    private static boolean check(String s) {
        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < n; i++) {
            if (secretCode.charAt(i) == s.charAt(i)) {
                bulls++;
            } else {
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        continue;
                    }
                    if (secretCode.charAt(i) == s.charAt(j)) {
                        cows++;
                    }
                }
            }
        }
        String prefix = "";
        if (bulls == 1) {
            prefix += bulls + " bull";
        }
        if (bulls > 1 && bulls <= n) {
            prefix += bulls + " bulls";
        }
        if (bulls >= 1 && cows >= 1) {
            prefix += " and ";
        }
        if (cows == 1) {
            prefix += cows + " cow";
        }
        if (cows > 1 && cows <= n) {
            prefix += cows + " cows ";
        }
        if (bulls == 0 && cows == 0) {
            prefix += "None. ";
        }
        if (bulls == n) {
            return true;
        } else {
            System.out.println(prefix);
            return false;
        }
    }
}
