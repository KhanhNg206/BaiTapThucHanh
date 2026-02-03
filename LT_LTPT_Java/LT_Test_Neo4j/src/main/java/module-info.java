module com.example.lt_test_neo4j {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lt_test_neo4j to javafx.fxml;
    exports com.example.lt_test_neo4j;
}