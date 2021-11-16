package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class TODOAppController {


    @FXML
    private ComboBox<String> curr_list;
    @FXML
    private TableView<Task> data_table;
    @FXML
    private TableColumn<Task, String> description;
    @FXML
    private TableColumn<Task, String> due_date;
    @FXML
    private TableColumn<Task, String> status;
    @FXML
    private TextField new_description;
    @FXML
    private DatePicker pick_due;



    ArrayList<TodoList> lists = new ArrayList<TodoList>();
    ObservableList<String> list_names = FXCollections.observableArrayList();


    @FXML
    void initialize() {
        //TODO
        reLoadTable();
    }

    //**************Events********************//
    @FXML
    void List_changed()
    {
        reLoadTable();
    }

    @FXML
    void show_all_pressed()
    {
        reLoadTable();
        data_table.refresh();
    }

    @FXML
    void show_complete()
    {
        TodoList temp_todo = findCurrentList();
        ObservableList<Task> temp_obs = null;
        if(temp_todo != null)
            temp_obs = temp_todo.getCompleted();

        if (temp_obs != null) {
            due_date.setCellValueFactory(new PropertyValueFactory<Task, String>("dueDate"));
            status.setCellValueFactory(new PropertyValueFactory<Task, String>("status"));
            description.setCellValueFactory(new PropertyValueFactory<Task, String>("description"));

            data_table.setItems(temp_obs);
        }
        data_table.refresh();
    }

    @FXML
    void show_incomplete()
    {
        TodoList temp_todo = findCurrentList();
        ObservableList<Task> temp_obs = null;
        if(temp_todo != null)
            temp_obs = temp_todo.getnotCompleted();

        if (temp_obs != null) {
            due_date.setCellValueFactory(new PropertyValueFactory<Task, String>("dueDate"));
            status.setCellValueFactory(new PropertyValueFactory<Task, String>("status"));
            description.setCellValueFactory(new PropertyValueFactory<Task, String>("description"));

            data_table.setItems(temp_obs);
        }
        data_table.refresh();
    }

    @FXML
    void Mark_complete_pressed(){
        Task temp = data_table.getSelectionModel().getSelectedItem();
        if(temp != null)
        {
            if(findCurrentList().changeStatus(temp))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Task Completed!");
                alert.setTitle("Congratulations!");
                alert.setHeaderText("Good Job!");
                alert.showAndWait();
                data_table.refresh();
            }
        }
    }


    @FXML
    void delete_list_pressed(ActionEvent event)
    {
        TodoList temp = findCurrentList();
        if(temp != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want delete "
                    + temp.getTitle() + "?");
            alert.setTitle("Task Deletion");
            alert.setHeaderText("WARNING");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                list_names.remove(temp.getTitle());
                empty_list_pressed(event);
                lists.remove(temp);
                reloadLists();
                data_table.refresh();
            }
        }


    }

    @FXML
    void rename_list_pressed() {
        TodoList temp = findCurrentList();
        if(temp != null)
        {
            list_names.remove(temp.getTitle());

            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Rename List ");
            dialog.setHeaderText("Please input the name for your new list.");
            dialog.setContentText("New List Name: ");
            Optional<String> result = dialog.showAndWait();

            if(result.isPresent() && !result.get().equals(""))
            {
                temp.setTitle(result.get());
            }
            reloadLists();
        }
    }

    @FXML
    void new_list_pressed(ActionEvent event) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add New List");
            dialog.setHeaderText("Please input the name for your new list.");
            dialog.setContentText("New List Name: ");
            Optional<String> result = dialog.showAndWait();


            if (result.isPresent()) {
                String listName = result.get();
                TodoList temp = new TodoList(listName);
                lists.add(temp);
                reloadLists();
            }
            else
                System.out.println("string was empty");

    }

    @FXML
    void Add_Task_pressed(ActionEvent event)
    {
        Task temp = null;

        if(findCurrentList() != null)
        {
            String dueDate = null;
            String desc = null;
            try {
                dueDate = pick_due.getValue().toString();
                desc = new_description.getText();
            } catch (Exception e) {
                System.out.println("Please Pick A Due Date.");
            }


            if(dueDate != null && !Objects.equals(desc, ""))
                temp = new Task(desc, dueDate);

            if(temp != null)
                findCurrentList().getTasks().add(temp);

            new_description.clear();
            reLoadTable();
        }

    }

    @FXML
    void Delete_Task_pressed(ActionEvent event)
    {
        Task temp = data_table.getSelectionModel().getSelectedItem();
        if(temp != null)
        {
            findCurrentList().getTasks().remove(temp);

            reLoadTable();
        }
    }

    @FXML
    void edit_task_pressed(ActionEvent event)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editTask.fxml"));
        if (data_table.getSelectionModel().getSelectedItem() != null) {
            try {
                Parent parent = loader.load();
                editTaskController taskController = loader.getController();
                taskController.setTask(data_table.getSelectionModel().getSelectedItem());

                Scene scene = new Scene(parent, 427, 254);
                Stage stage = new Stage();
                stage.setTitle("Edit Task");
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        data_table.refresh();
    }

    @FXML
    void empty_list_pressed(ActionEvent event)
    {
        if(findCurrentList() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete all items?");
            alert.setTitle("DELETE ALL ITEMS");
            alert.setHeaderText("WARNING");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                findCurrentList().getTasks().remove(0, findCurrentList().getTasks().size());
            }
        }
        data_table.refresh();
    }

    @FXML
    void load_list_pressed(ActionEvent event) throws FileNotFoundException {
        FileChooser fc = new FileChooser();
        fc.setTitle("Open a new List");
        File ifp = fc.showOpenDialog(null);
        if(ifp != null)
        {
            if(ifp.getName().contains(".csv"))
            {
                try {
                        String[] data = null;
                        BufferedReader reader = new BufferedReader(new FileReader(ifp));
                        String line = "";
                        TodoList loadedList = new TodoList(reader.readLine());
                        while (((line = reader.readLine()) != null)) {
                            data = line.split(",");
                            loadedList.getTasks().add(createTask(data));
                        }

                        lists.add(loadedList);
                    } catch (IOException e) {
                        e.printStackTrace();
                }
            }
        }

        data_table.refresh();
        reloadLists();
    }

    @FXML
    void save_list_pressed() throws IOException {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Save Current List");
        String location;
        File ofp = dc.showDialog(null);
        if(ofp != null && ofp.isDirectory() && findCurrentList() != null)
        {
            location = ofp.getAbsolutePath().concat("\\").concat(findCurrentList().getTitle() + ".csv");
            File export = new File(location);
            if(export.createNewFile())
            {
                System.out.println("File Created");
                File export_file = new File(location);
                FileWriter writer = new FileWriter(export_file);

                writer.write(findCurrentList().setUpExport());
                writer.close();
            } else {
                System.out.println("Not Created");
            }

        }
    }

    @FXML
    void open_help_pane()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("helpPane.fxml"));
            try {
                Parent parent = loader.load();
                Scene scene = new Scene(parent, 720, 412);
                Stage stage = new Stage();
                stage.setTitle("Help");
                stage.setResizable(false);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    //******************helper**************//

    TodoList findCurrentList()
    {
        for(TodoList list : lists)
        {
            if(list.getTitle().equals(curr_list.getValue()))
                return list;
        }

        return null;
    }

    void reloadLists()
    {
        for(TodoList list : lists)
        {
            if(list_names.contains(list.getTitle()))
                continue;
            list_names.add(list.getTitle());
        }

        curr_list.setItems(list_names);
    }

    void reLoadTable()
    {
        TodoList temp_todo = findCurrentList();
        ObservableList<Task> temp_obs = null;
        if(temp_todo != null)
            temp_obs = temp_todo.getTasks();

        if (temp_obs != null) {
            due_date.setCellValueFactory(new PropertyValueFactory<Task, String>("dueDate"));
            status.setCellValueFactory(new PropertyValueFactory<Task, String>("status"));
            description.setCellValueFactory(new PropertyValueFactory<Task, String>("description"));

            data_table.setItems(temp_obs);
        }
    }

    Task createTask(String[] Data){
        Task createdTask;

        int i = 0;

        LocalDate date = LocalDate.parse(Data[i]);
        boolean status = Data[i + 1].contains("true");
        String description = Data[i + 2];

        createdTask = new Task(description,date.toString());
        createdTask.setStatus(status);


        return createdTask;
    }

}
