module com.example.fastfoodpos_rev3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.fastfoodpos_rev3 to javafx.fxml;
    exports com.example.fastfoodpos_rev3;
    exports com.example.zzzfastfood_test4;
    opens com.example.zzzfastfood_test4 to javafx.fxml;
}