package com.mat.matrx;


import javafx.scene.control.TextArea;

public class MatrixController {

    private static final String DIRECTORY_PATH = "src\\main\\resources\\";
    private static final String MATRIX1_FILE = "matrix1.txt";
    private static final String MATRIX2_FILE = "matrix2.txt";

    public void showResults(TextArea matrixOutput) {
        double[][] matrix1 = MatrixOperations.readMatrixFromFile(DIRECTORY_PATH + MATRIX1_FILE);
        double[][] matrix2 = MatrixOperations.readMatrixFromFile(DIRECTORY_PATH + MATRIX2_FILE);

        if (matrix1 == null || matrix2 == null) {
            matrixOutput.setText("Ошибка при чтении файлов");
            return;
        }

        StringBuilder output = new StringBuilder();
        // Вывод исходных матриц
        output.append("Матрица 1:\n").append(matrixToString(matrix1)).append("\n");
        output.append("Матрица 2:\n").append(matrixToString(matrix2)).append("\n");

        MatrixOperations operations = new MatrixOperations();

        // Проверка и выполнение сложения и вычитания
        if (matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length) {
            output.append("\nМатрицы разного размера, невозможно совершить сложение и вычитание");
        } else {
            double[][] sum = operations.addMatrices(matrix1, matrix2);
            double[][] difference = operations.subtractMatrices(matrix1, matrix2);
            output.append("\nСумма:\n").append(matrixToString(sum));
            output.append("\nРазность:\n").append(matrixToString(difference));
        }

        // Проверка и выполнение умножения
        if (matrix1[0].length == matrix2.length) {
            double[][] product = operations.multiplyMatrices(matrix1, matrix2);
            output.append("\nПроизведение:\n").append(matrixToString(product));
        } else {
            output.append("\nНевозможно выполнить умножение матриц: количество столбцов первой \nматрицы не равно количеству строк второй матрицы");
        }

        // Проверка и вычисление определителей
        output.append("\n");
        checkAndAppendDeterminant(matrix1, output, 1);
        checkAndAppendDeterminant(matrix2, output, 2);

        matrixOutput.setText(output.toString());
    }

    private void checkAndAppendDeterminant(double[][] matrix, StringBuilder output, int matrixNumber) {
        if (isSquareMatrix(matrix)) {
            MatrixOperations operations = new MatrixOperations();
            double det = operations.determinant(matrix);
            output.append("\nОпределитель матрицы ").append(matrixNumber).append(": ").append(det);
        } else {
            output.append("\nМатрица ").append(matrixNumber)
                    .append(" не является квадратной, невозможно найти определитель");
        }
    }

    private boolean isSquareMatrix(double[][] matrix) {
        return matrix.length == matrix[0].length;
    }

    private String matrixToString(double[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (double[] row : matrix) {
            for (double element : row) {
                sb.append(String.format("%8.2f ", element));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

