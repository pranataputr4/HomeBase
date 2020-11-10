module com.mycompany.homebase {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;

    opens com.mycompany.homebase to javafx.fxml;
    exports com.mycompany.homebase;
}
