module com.example.mainApp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires com.jfoenix;
    //requires java.persistence;
    requires java.naming;
    //requires javax.persistence;
    requires jakarta.persistence;
    //requires jboss.logging;

    opens com.example.mainApp to javafx.fxml;
    exports com.example.mainApp;
    opens com.example.mainApp.projekt_z_javy.entity to org.hibernate.orm.core;
    exports com.example.mainApp.sql;
    opens com.example.mainApp.sql to javafx.fxml;
}