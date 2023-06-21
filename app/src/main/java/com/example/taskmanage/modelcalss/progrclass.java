package com.example.taskmanage.modelcalss;

public class progrclass {
    String titeName;
    int progrBar;


    public progrclass() {
    }

    public progrclass(String titeName, int progrBar) {
        this.titeName = titeName;
        this.progrBar = progrBar;
    }

    public String getTiteName() {
        return titeName;
    }

    public void setTiteName(String titeName) {
        this.titeName = titeName;
    }

    public int getProgrBar() {
        return progrBar;
    }

    public void setProgrBar(int progrBar) {
        this.progrBar = progrBar;
    }

}
