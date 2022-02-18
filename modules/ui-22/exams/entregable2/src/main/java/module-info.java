module com.github.pomaretta.cide.ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens com.github.pomaretta.cide.ui to javafx.fxml;
    exports com.github.pomaretta.cide.ui;
}
