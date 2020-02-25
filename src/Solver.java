import java.util.Arrays;

public class Solver {
    private double[][] colA;
    private double[][] colB;

    public Solver(double[][] colA, double[][] colB) {
        this.colA = colA;
        this.colB = colB;
    }

    public boolean solve(String workingMatrixID, int targetVectorIndex, double[] coefficients, boolean verbose) {
        boolean result;
        double[] resultantVector = new double[5];
        double[][] workingMatrix = workingMatrixID.equals("A") ? colA : colB;
        double[] workingVector;
        double[][] targetMatrix = workingMatrixID.equals("A") ? colB : colA;
        double[] targetVector = targetMatrix[targetVectorIndex];

        if (verbose) {
            System.out.println("Target vector:");
            for (double entry : targetVector) {
                System.out.println(entry);
            }
        }

        for (int i = 0; i < workingMatrix.length; i++) {
            workingVector = workingMatrix[i];
            for (int j = 0; j < workingMatrix[i].length; j++) {
                resultantVector[j] += workingVector[j] * coefficients[i];
            }
        }
        for (int i = 0; i < resultantVector.length; i++) {
            resultantVector[i] = Math.round(resultantVector[i]);
        }
        result = Arrays.equals(resultantVector, targetVector);

        if (verbose) {
            System.out.println("Resultant vector:");
            for (double entry : resultantVector) {
                System.out.println(entry);
            }
            System.out.println("The vectors are " + (result ? "" : "not ") + "equal.\n");
        }

        return result;
    }
}
