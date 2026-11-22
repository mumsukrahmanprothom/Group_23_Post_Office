module com.example.group_23_post_office {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.group_23_post_office to javafx.fxml;
    exports com.example.group_23_post_office;
}