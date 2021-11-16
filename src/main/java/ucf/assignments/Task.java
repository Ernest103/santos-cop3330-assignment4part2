package ucf.assignments;

/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Ernesto Santos
 */

import java.time.chrono.Chronology;

public class Task {
    private String description;
    private boolean status;
    private String dueDate;

    public Task(String description, String dueDate) {
        this.description = description;
        this.status = true;
        this.dueDate = dueDate;
    }

    private String formatDate(String unFormatted)
    {
        return null;
    }


    //*******Getters and Setters**********//

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String isStatus() {
        if(status)
            return "Not Complete.";
        return "Complete";
    }

    public boolean isStatusbool()
    {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

}
