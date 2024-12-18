package com.mat.matrx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        MatrixController controller = new MatrixController();

        TextArea matrixOutput = new TextArea();
        matrixOutput.setEditable(false);
        matrixOutput.setPrefHeight(600);

        VBox root = new VBox(matrixOutput);
        root.setSpacing(10);
        root.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(root);
        primaryStage.setTitle("Matrix Operations");
        primaryStage.setScene(scene);
        primaryStage.show();

        controller.showResults(matrixOutput);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
