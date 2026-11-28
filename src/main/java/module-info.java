module com.postoffice {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.group_23_post_office to javafx.fxml;

    opens com.postoffice to javafx.fxml;
    opens com.postoffice.controller to javafx.fxml;
    opens com.postoffice.controllers to javafx.fxml;
    opens com.postoffice.controllers.user7 to javafx.fxml;
    opens com.postoffice.controllers.user8 to javafx.fxml;

    opens com.postoffice.model to javafx.base;
    opens com.postoffice.models to javafx.base;

    exports com.example.group_23_post_office;
    exports com.postoffice;
}