import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("What is your data type? (string|integer|double): ");
        String dataType = scanner.next();

        System.out.print("Number of elements: ");
        int n = scanner.nextInt();

        System.out.print("Type of input (manual|random): ");
        String inputType = scanner.next();

        String[] a = new String[n];
        switch (inputType) {
            case "manual" -> {
                System.out.print("Input elements: ");
                for (int i = 0; i < n; i++)
                    a[i] = scanner.next();
            }
            case "random" -> {
                Random random = new Random();
                for (int i = 0; i < n; i++)
                    switch (dataType) {
                        case "string" -> {
                            a[i] = "";
                            for (int j = random.nextInt(100); j >= 0; j--)
                                a[i] = a[i] + (char) ('a' + random.nextInt(26));
                        }
                        case "integer" -> a[i] = String.valueOf(Math.abs(random.nextInt((int) 1e9)));
                        case "double" -> a[i] = String.valueOf(Math.abs(random.nextDouble()));
                        default -> {
                            System.out.println("Invalid data type!");
                            System.exit(-1);
                        }
                    }
            }
            default -> {
                System.out.println("Invalid input type!");
                System.exit(-1);
            }
        }

        switch (dataType) {
            case "string" -> SortMethodSelector.analyseDataForStringInput(n, Arrays.copyOf(a, n));
            case "integer" -> SortMethodSelector.analyseDataForInteger(n, Arrays.stream(a).mapToInt(Integer::parseInt).toArray());
            case "double" -> SortMethodSelector.analyseDataForDouble(n, Arrays.stream(a).mapToDouble(Double::parseDouble).toArray());
            default -> {
                System.out.println("Invalid data type!");
                System.exit(-1);
            }
        }
    }
}
