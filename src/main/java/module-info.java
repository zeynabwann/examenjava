module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jpackage;
    requires org.hibernate.orm.core;

    opens com.example to javafx.fxml;
    exports com.example;
}
