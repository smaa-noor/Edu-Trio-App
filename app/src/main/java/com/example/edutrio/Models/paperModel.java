package com.example.edutrio.Models;

public class paperModel {
    String subject;
    String year;
    String discipline;
    String group;
    String board;

    public paperModel(){}

    public paperModel(String subject, int year, String discipline, String group, String board){
        this.subject = subject;
        this.year = String.valueOf(year);
        this.discipline = discipline;
        this.group = group;
        this.board = board;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setYear(int year) {
        this.year = String.valueOf(year);
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getSubject() {
        return subject;
    }

    public int getYear() {
        return Integer.parseInt(year);
    }

    public String getDiscipline() {
        return discipline;
    }

    public String getGroup() {
        return group;
    }

    public String getBoard() {
        return board;
    }
}
