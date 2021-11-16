package ucf.assignments;

/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Ernesto Santos
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TodoList {
    private String title;
    private ObservableList<Task> Tasks;

    public TodoList(String title) {
        this.title = title;
        this.Tasks = FXCollections.observableArrayList();
    }


    public boolean changeStatus(Task toUpdate)
    {
        //Get the Task to be updated
        //Check if Task status is not "Complete"
        //Change status to complete

        if(Tasks.contains(toUpdate))
        {
            int index = Tasks.indexOf(toUpdate);
            if(Tasks.get(index).isStatusbool())
            {
                Tasks.get(index).setStatus(false);
                //Success
                return true;
            }
        }

        //Fail
        return false;
    }

    public ObservableList<Task> getCompleted()
    {
        //Will create an array list holding only the Tasks which are completed
        //In order for it to be displayed in the app controller

        ObservableList<Task> retList = FXCollections.observableArrayList();
        for(Task task : Tasks)
        {
            if (!task.isStatusbool())
                retList.add(task);
        }

        //Return the new Array list
        return retList;
    }

    public ObservableList<Task> getnotCompleted()
    {
        //Will create an array list holding only the Tasks which are Active
        //In order for it to be displayed in the app controller
        ObservableList<Task> retList = FXCollections.observableArrayList();
        for(Task task : Tasks)
        {
            if (task.isStatusbool())
                retList.add(task);
        }

        //Return the new Array list
        return retList;
    }


    public String setUpExport()
    {
        String retString = this.title.concat("\n");

        for(Task task : Tasks)
        {
            StringBuilder exportLine = new StringBuilder();
            retString = retString.concat(exportLine.append(task.getDueDate()).append(", ")
                    .append(task.isStatusbool()).append(", ")
                    .append(task.getDescription()).append("\n").toString());
        }

        return retString;
    }

    //******getters and setters*******//

    public void setTasks(ObservableList<Task> tasks) {
        Tasks = tasks;
    }

    public ObservableList<Task> getTasks() {
        return Tasks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
