module com.github.pomaretta.cide {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.logging;
    requires transitive javafx.graphics;

    opens com.github.pomaretta.cide to javafx.fxml;
    exports com.github.pomaretta.cide;
}
