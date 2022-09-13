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
    //requires java.persistence;
    requires java.naming;
    //requires javax.persistence;
    requires jakarta.persistence;
    //requires jboss.logging;

    opens com.example.projekt_z_javy to javafx.fxml;
    exports com.example.projekt_z_javy;
    opens com.example.projekt_z_javy.entity to org.hibernate.orm.core;
}