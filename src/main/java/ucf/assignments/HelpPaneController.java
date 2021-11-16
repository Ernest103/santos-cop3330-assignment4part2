package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class HelpPaneController {


        @FXML
        void ok_button_pressed(ActionEvent event) {
            Node source = (Node)  event.getSource();
            Stage stage  = (Stage) source.getScene().getWindow();
            stage.close();
        }

}

