package model;

import java.time.LocalDate;

public abstract class AbstractTask {
    protected int id;
    protected String name;
    protected String category;
    protected LocalDate deadline;


    public AbstractTask(int id, String name, String category, LocalDate deadline) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.deadline = deadline;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public abstract void display();
}