module com.mat.matrx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.mat.matrx to javafx.fxml;
    exports com.mat.matrx;
}