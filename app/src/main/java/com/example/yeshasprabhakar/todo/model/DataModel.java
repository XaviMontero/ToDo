package com.example.yeshasprabhakar.todo.model;

public class DataModel {

    private String title;
    private String date;
    private String time;
    private String categoria;
    private String descripcion;


    public DataModel(String title, String date, String time, String categoria, String descripcion) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

}
