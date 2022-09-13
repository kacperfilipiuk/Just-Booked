module com.example.projekt_z_javy {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires com.jfoenix;
    requires java.persistence;
    requires java.naming;
    //requires hibernate.core;
    //requires jboss.logging;

    opens com.example to javafx.fxml;
    exports com.example;
    exports newImplementation;
    opens newImplementation to javafx.fxml;
}