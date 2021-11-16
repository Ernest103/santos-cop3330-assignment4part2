package ucf.assignments;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;


public class editTaskController {

    @FXML
    private TextField new_desc;

    @FXML
    private DatePicker new_due_date;

    private Task toUpdate;

    public void setTask(Task mainTask)
    {
        this.toUpdate = mainTask;
        new_desc.setText(toUpdate.getDescription());
        new_due_date.setValue(LocalDate.parse(toUpdate.getDueDate()));
    }

    @FXML
    void confirmButtonPressed(ActionEvent event) {
        if(!new_desc.getText().isEmpty())
        {
            toUpdate.setDescription(new_desc.getText());
            toUpdate.setDueDate(new_due_date.getValue().toString());
        }

        closeStage(event);
    }

    @FXML
    void cancelButtonPressed(ActionEvent event)
    {
        closeStage(event);
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
