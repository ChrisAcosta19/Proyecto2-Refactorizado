module grupo8.proyectofifa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens grupo8.proyectofifa to javafx.fxml;
    exports grupo8.proyectofifa;
}
