module com.rpl.homebase {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires javafx.base;
    requires javafx.graphics;

    opens com.rpl.homebase to javafx.fxml;
    exports com.rpl.homebase;
    opens com.rpl.homebase.model to javafx.base;
}
