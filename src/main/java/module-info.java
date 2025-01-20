module com.haven.app.haven {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.haven.app.haven to javafx.fxml;
    exports com.haven.app.haven;
    //exports Controller;
    //opens Controller to javafx.fxml;
}