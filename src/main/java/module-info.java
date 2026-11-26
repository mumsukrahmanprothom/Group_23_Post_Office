module com.postoffice {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.postoffice to javafx.fxml;
    opens com.postoffice.controller to javafx.fxml;
    opens com.postoffice.model to javafx.base;

    exports com.postoffice;
}
