package com.mat.matrx;

import java.io.*;

public class MatrixOperations {
    // Определяем константы с путями к файлам
    private static final String DIRECTORY_PATH = "src\\main\\resources\\"; // Путь к директории с файлами
    private static final String MATRIX1_FILE = "matrix1.txt";    // Имя первого файла
    private static final String MATRIX2_FILE = "matrix2.txt";    // Имя второго файла

    public static void main(String[] args) {
        // Чтение матриц из файлов
        double[][] matrix1 = readMatrixFromFile(DIRECTORY_PATH + MATRIX1_FILE);
        double[][] matrix2 = readMatrixFromFile(DIRECTORY_PATH + MATRIX2_FILE);

        if (matrix1 == null || matrix2 == null) {
            System.out.println("Ошибка при чтении файлов");
            return;
        }

        // Вывод исходных матриц
        System.out.println("\nМатрица 1:");
        printMatrix(matrix1);
        System.out.println("\nМатрица 2:");
        printMatrix(matrix2);

        // Операции с матрицами
        if (matrix1.length == matrix2.length && matrix1[0].length == matrix2[0].length) {
            System.out.println("\nСумма:");
            printMatrix(addMatrices(matrix1, matrix2));

            System.out.println("\nРазность:");
            printMatrix(subtractMatrices(matrix1, matrix2));
        } else {
            System.out.println("\nМатрицы разного размера. Сложение и вычитание невозможны.");
        }

        if (matrix1[0].length == matrix2.length) {
            System.out.println("\nПроизведение:");
            printMatrix(multiplyMatrices(matrix1, matrix2));
        } else {
            System.out.println("\nМатрицы разного размера. Умножение невозможно.");
        }

        if (matrix1.length == matrix1[0].length) {
            System.out.println("\nОпределитель матрицы 1: " + determinant(matrix1));
        }
        if (matrix2.length == matrix2[0].length) {
            System.out.println("Определитель матрицы 2: " + determinant(matrix2));
        }
    }

    // Чтение матрицы из файла
    public static double[][] readMatrixFromFile(String filepath) {
        try {
            File file = new File(filepath);
            if (!file.exists()) {
                System.out.println("Файл не найден: " + filepath);
                return null;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            int rows = 0;
            int cols = 0;

            // Подсчет размеров матрицы
            while ((line = reader.readLine()) != null) {
                rows++;
                String[] elements = line.trim().split("\\s+");
                cols = elements.length;
            }

            reader.close();

            // Создание и заполнение матрицы
            double[][] matrix = new double[rows][cols];
            reader = new BufferedReader(new FileReader(file));
            int row = 0;

            while ((line = reader.readLine()) != null) {
                String[] elements = line.trim().split("\\s+");
                for (int col = 0; col < elements.length; col++) {
                    matrix[row][col] = Double.parseDouble(elements[col]);
                }
                row++;
            }

            reader.close();
            return matrix;
        } catch (IOException | NumberFormatException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
            return null;
        }
    }

    // Сложение матриц
    public static double[][] addMatrices(double[][] matrix1, double[][] matrix2) {
        int rows = matrix1.length;
        int cols = matrix1[0].length;
        double[][] result = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return result;
    }

    // Вычитание матриц
    public static double[][] subtractMatrices(double[][] matrix1, double[][] matrix2) {
        int rows = matrix1.length;
        int cols = matrix1[0].length;
        double[][] result = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }
        return result;
    }
    // Умножение матриц
    public static double[][] multiplyMatrices(double[][] matrix1, double[][] matrix2) {
        int rows1 = matrix1.length;
        int cols1 = matrix1[0].length;
        int cols2 = matrix2[0].length;
        double[][] result = new double[rows1][cols2];

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                for (int k = 0; k < cols1; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

    // Вычисление определителя матрицы
    public static double determinant(double[][] matrix) {
        if (matrix.length == 1) {
            return matrix[0][0];
        }
        if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        double det = 0;
        for (int i = 0; i < matrix.length; i++) {
            det += Math.pow(-1, i) * matrix[0][i] * determinant(getMinor(matrix, 0, i));
        }
        return det;
    }

    // Получение минора матрицы
    public static double[][] getMinor(double[][] matrix, int row, int col) {
        int n = matrix.length;
        double[][] minor = new double[n-1][n-1];
        int r = 0, c = 0;

        for (int i = 0; i < n; i++) {
            if (i == row) continue;
            c = 0;
            for (int j = 0; j < n; j++) {
                if (j == col) continue;
                minor[r][c] = matrix[i][j];
                c++;
            }
            r++;
        }
        return minor;
    }

    // Вывод матрицы на экран
    private static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double element : row) {
                System.out.printf("%8.2f ", element);
            }
            System.out.println();
        }
    }
}
