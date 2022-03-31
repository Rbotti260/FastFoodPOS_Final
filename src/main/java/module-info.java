module com.example.fastfoodpos_rev3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fastfoodpos_rev3 to javafx.fxml;
    exports com.example.fastfoodpos_rev3;
}