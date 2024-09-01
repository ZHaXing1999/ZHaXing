module test.managergui {
    requires javafx.controls;
    requires javafx.fxml;
    requires snmp4j;
    requires junit;


    opens test.managergui to javafx.fxml;
    exports test.managergui;
}