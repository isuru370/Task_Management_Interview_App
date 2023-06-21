package com.example.taskmanage.modelcalss;

public class task {
    String title;
    String descrption;
    String dueDate;
    String proptyLavel;
    String category;

    public task() {
    }

    public task(String title, String descrption, String dueDate, String proptyLavel, String category) {
        this.title = title;
        this.descrption = descrption;
        this.dueDate = dueDate;
        this.proptyLavel = proptyLavel;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getProptyLavel() {
        return proptyLavel;
    }

    public void setProptyLavel(String proptyLavel) {
        this.proptyLavel = proptyLavel;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
