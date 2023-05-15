module com.example.javafxdemo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.javafxdemo to javafx.fxml;
    exports com.example.javafxdemo;
    exports com.example.partie1;
    exports com.example.partie2;
    exports com.example.partie2.PacMan;
    exports com.example.partie2.Pendu;
}