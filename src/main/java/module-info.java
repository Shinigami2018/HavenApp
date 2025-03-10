module com.haven.app.haven {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
    requires javafx.web;
    requires javafx.media;
    requires java.desktop; // Add this line

    opens com.haven.app.haven to javafx.fxml;
    exports com.haven.app.haven;
}

