import java.io.*;
import java.util.*;

public class MatrixMultiplier {
    public static void main(String[] args) throws IOException {
        if (args.length == 1 && isInteger(args[0])) {
            int size = Integer.parseInt(args[0]);
            int[][] matrix1 = generateMatrix(size);
            int[][] matrix2 = generateMatrix(size);

            writeToFile(matrix1, "matrix1.txt");
            writeToFile(matrix2, "matrix2.txt");

            int[][] result = multiply(matrix1, matrix2);
            writeToFile(result, "matrix3.txt");

            System.out.println("Generated matrices and saved result to matrix3.txt");
        } else if (args.length == 2) {
            int[][] matrix1 = readFromFile(args[0]);
            int[][] matrix2 = readFromFile(args[1]);

            int[][] result = multiply(matrix1, matrix2);
            writeToFile(result, "matrix3.txt");

            System.out.println("Read matrices from files and saved result to matrix3.txt");
        } else {
            System.out.println("Usage: java MatrixMultiplier <size> OR <file1> <file2>");
        }
    }

    static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    static int[][] generateMatrix(int size) {
        Random rand = new Random();
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                matrix[i][j] = rand.nextInt(10);
        return matrix;
    }

    static void writeToFile(int[][] matrix, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (int[] row : matrix) {
            for (int num : row) {
                writer.write(num + " ");
            }
            writer.newLine();
        }
        writer.close();
    }

    static int[][] readFromFile(String fileName) throws IOException {
        List<int[]> rows = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] nums = line.trim().split("\\s+");
            int[] row = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                row[i] = Integer.parseInt(nums[i]);
            }
            rows.add(row);
        }
        reader.close();
        return rows.toArray(new int[0][]);
    }

    static int[][] multiply(int[][] a, int[][] b) {
        int rows = a.length;
        int cols = b[0].length;
        int sharedDim = a[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                for (int k = 0; k < sharedDim; k++)
                    result[i][j] += a[i][k] * b[k][j];

        return result;
    }
}

