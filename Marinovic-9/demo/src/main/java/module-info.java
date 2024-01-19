module hr.java.shop.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens hr.java.shop.demo to javafx.fxml;
    exports hr.java.shop.demo;
}