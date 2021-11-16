import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ucf.assignments.Task;
import ucf.assignments.TodoList;

/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Ernesto Santos
 */


public class TodoListTest {

    /**
     *
     * Method: changeStatus(Task toUpdate)
     *
     */
    @Test
    public void testChangeStatus() throws Exception {
        TodoList list = new TodoList("my test list");
        list.getTasks().add(new Task("test task", "2007-12-12"));

        boolean statusBefore = list.getTasks().get(0).isStatusbool();
        list.changeStatus(list.getTasks().get(0));
        boolean statusAfter = list.getTasks().get(0).isStatusbool();

        Assertions.assertNotEquals(statusAfter, statusBefore);
    }

    /**
     *
     * Method: getCompleted()
     *
     */
    @Test
    public void testGetCompleted() throws Exception {
        TodoList list = new TodoList("my test list");
        list.getTasks().add(new Task("test task", "2007-12-12"));
        list.getTasks().add(new Task("test completed task", "2007-12-12"));
        list.getTasks().get(1).setStatus(false);

        ObservableList<Task> completed = list.getCompleted();
        Assertions.assertTrue(completed.contains(list.getTasks().get(1)));
    }

    /**
     *
     * Method: getnotCompleted()
     *
     */
    @Test
    public void testGetnotCompleted() throws Exception {
        TodoList list = new TodoList("my test list");
        list.getTasks().add(new Task("test task", "2007-12-12"));
        list.getTasks().add(new Task("test completed task", "2007-12-12"));
        list.getTasks().get(1).setStatus(false);

        ObservableList<Task> completed = list.getCompleted();
        Assertions.assertFalse(completed.contains(list.getTasks().get(1)));
    }

    /**
     *
     * Method: setUpExport()
     *
     */
    @Test
    public void testSetUpExport() throws Exception {
        String export = "2007-12-01, false, Something to do 1\n" +
                "2007-12-02, true, Something to do 2";
        TodoList list = new TodoList("my test list");
        list.getTasks().add(new Task("2007-12-01","Something to do 1"));
        list.getTasks().add(new Task("2007-12-01","Something to do 2"));

        String generated = list.setUpExport();
        Assertions.assertEquals(export, generated);
    }

    /**
     *
     * Method: setTasks(ObservableList<Task> tasks)
     *
     */
    @Test
    public void testSetTasks() throws Exception {
        TodoList list = new TodoList("my test list");
        ObservableList<Task> temp = FXCollections.observableArrayList();
        temp.add(new Task("2007-12-01","Something to do 1"));
        temp.add(new Task("2007-12-01","Something to do 2"));

        list.setTasks(temp);

        Assertions.assertTrue(list.getTasks().contains(temp.get(0)));
    }

    /**
     *
     * Method: getTasks()
     *
     */
    @Test
    public void testGetTasks() throws Exception {
        TodoList list = new TodoList("my test list");
        list.getTasks().add(new Task("test task", "2007-12-12"));
        list.getTasks().add(new Task("test completed task", "2007-12-12"));

        ObservableList<Task> temp = list.getTasks();
        Assertions.assertEquals(temp, list.getTasks());
    }

    /**
     *
     * Method: getTitle()
     *
     */
    @Test
    public void testGetTitle() throws Exception {
        String title = "my test list";
        TodoList list = new TodoList(title);
        list.getTasks().add(new Task("test task", "2007-12-12"));
        list.getTasks().add(new Task("test completed task", "2007-12-12"));

        Assertions.assertEquals(title, list.getTitle());
    }

    /**
     *
     * Method: setTitle(String title)
     *
     */
    @Test
    public void testSetTitle() throws Exception {
        String title = "my test list";
        TodoList list = new TodoList(title);
        list.getTasks().add(new Task("test task", "2007-12-12"));
        list.getTasks().add(new Task("test completed task", "2007-12-12"));

        Assertions.assertEquals(title, list.getTitle());

        title = "New Title for List";
        list.setTitle(title);

        Assertions.assertEquals(title, list.getTitle());
    }

}