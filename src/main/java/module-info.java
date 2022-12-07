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

    opens com.example.mainApp to javafx.fxml, java.base;
    exports com.example.mainApp;
    opens com.example.mainApp.Entity to org.hibernate.orm.core, javafx.fxml, java.base;
    exports com.example.mainApp.Entity;
    exports com.example.mainApp.SQL;
    opens com.example.mainApp.SQL to javafx.fxml;
    exports com.example.mainApp.Controllers;
    opens com.example.mainApp.Controllers to java.base, javafx.fxml;
    exports com.example.mainApp.Hibernate;
    opens com.example.mainApp.Hibernate to java.base, javafx.fxml;
}