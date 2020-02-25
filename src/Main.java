import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean verbose = false;
        boolean allChecksPassed = false;
        int numChecksPassed = 0;
        int numChecks = 0;

        if (args.length >= 1) {
            verbose = args[1].equals("Y");
        } else {
            System.out.println("Show verbose output? [Y/N]");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("Y")) {
                verbose = true;
            } else if (!input.equalsIgnoreCase("N")){
                System.out.println("Invalid input.");
                System.exit(1);
            }
        }

        double[][] colA = {
                {-41, -2, -86, 11, -3},
                {-68, -18, -120, 22, -4},
                {12, -4, 32, 0, 0},
                {-65, 3, -94, 22, -3}
        };
        double[][] colB = {
                {-4, 7, -2, 3, -1},
                {4, 5, 8, -1, 0},
                {5, -2, 8, -1, 0},
                {-4, 2, 0, 2, 0}
        };
        double[][] bCoefs = {
                {0.7955, -0.6023, 0.8182, 0.3409, 0},
                {0.3182, -0.3409, 0.2273, 0.1364, 0},
                {0.0682, 0.0341, 0.2273, -0.1136, 0},
                {-0.1364, -0.0682, 0.0455, 0.2273, 0}
        };
        double[][] aCoefs = {
                {3, -5, -5, -4, 0},
                {4, -10, -4, -2, 0},
                {0, 0, 4, 2, 0},
                {3, -6, -5, 1, 0}
        };

        Solver solver = new Solver(colA, colB);
        for (int i = 0; i < aCoefs.length; i++) {
            numChecks++;
            if (solver.solve("A", i, bCoefs[i], verbose)) {
                numChecksPassed++;
            }
        }
        for (int i = 0; i < aCoefs.length; i++) {
            numChecks++;
            if (solver.solve("B", i, aCoefs[i], verbose)) {
                numChecksPassed++;
            }
        }
        allChecksPassed = numChecksPassed == numChecks;

        if (!verbose) {
            System.out.println();
        }
        System.out.println((allChecksPassed ? "A" : "Not a") + "ll of the resultant vectors are equal to the target vectors.\nThe subspaces formed by colA and colB are " + (allChecksPassed ? "" : "not ") + "the same.");
    }
}
