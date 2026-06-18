module pg07algoritmos {
    requires java.desktop;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;


    opens pg07algoritmos to javafx.fxml;
    opens pg07algoritmos.model to javafx.base;
    exports pg07algoritmos;
    exports pg07algoritmos.controller;
    opens pg07algoritmos.controller to javafx.fxml;

    exports pg07algoritmos.util;
    opens pg07algoritmos.util to javafx.fxml;
    opens pg07algoritmos.model.linkedList to javafx.base;
    opens pg07algoritmos.model.stack to javafx.base;
    opens pg07algoritmos.model.Queue to javafx.base;
    opens pg07algoritmos.model.Tree to javafx.base;
}