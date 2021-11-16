
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ucf.assignments.Task;

/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Ernesto Santos
 */


public class taskTest {

    /**
     *
     * Method: getDescription()
     *
     */
    @Test
    public void testGetDescription() throws Exception {
        Task testTask = new Task("Test Description", "2021-11-15");
        String check = "Test Description";

        Assertions.assertEquals(check, testTask.getDescription());
    }

    /**
     *
     * Method: setDescription(String description)
     *
     */
    @Test
    public void testSetDescription() throws Exception {
        Task testTask = new Task("Test Description", "2021-11-15");
        String check = "New Description";
        testTask.setDescription(check);

        Assertions.assertEquals(check, testTask.getDescription());
    }

    /**
     *
     * Method: isStatus()
     *
     */
    @Test
    public void testIsStatus() throws Exception {
        Task testTask = new Task("Test Description", "2021-11-15");

        Assertions.assertTrue(testTask.isStatusbool());
    }

    /**
     *
     * Method: setStatus(boolean status)
     *
     */
    @Test
    public void testSetStatus() throws Exception {
        Task testTask = new Task("Test Description", "2021-11-15");
        testTask.setStatus(false);

        Assertions.assertFalse(testTask.isStatusbool());    }

    /**
     *
     * Method: getDueDate()
     *
     */
    @Test
    public void testGetDueDate() throws Exception {
        Task testTask = new Task("Test Description", "2021-11-15");
        String check = "2021-11-15";

        Assertions.assertEquals(check, testTask.getDueDate());
    }

    /**
     *
     * Method: setDueDate(String dueDate)
     *
     */
    @Test
    public void testSetDueDate() throws Exception {
        Task testTask = new Task("Test Description", "2021-11-15");
        String check = "2021-12-12";
        testTask.setDueDate(check);

        Assertions.assertEquals(check, testTask.getDueDate());
    }

}
