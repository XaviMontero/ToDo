package com.example.yeshasprabhakar.todo.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Doctor {
    public String cedula;
    public String nombre;
    public String especialidad;

    public Doctor(String cedula, String nombre, String especialidad) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public String cedula() {
        return cedula;
    }

    public String nombre() {
        return nombre;
    }

    public String especialidad() {
        return especialidad;
    }

    public Doctor() {
    }
}
