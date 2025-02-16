package com.example;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Game {
    private final SimpleIntegerProperty posicion;
    private final SimpleStringProperty nombre;
    private final SimpleIntegerProperty puntuacion;

    public Game(int posicion, String nombre, int puntuacion) {
        this.posicion = new SimpleIntegerProperty(posicion);
        this.nombre = new SimpleStringProperty(nombre);
        this.puntuacion = new SimpleIntegerProperty(puntuacion);
    }

    public int getPosicion() {
        return posicion.get();
    }

    public String getNombre() {
        return nombre.get();
    }

    public int getPuntuacion() {
        return puntuacion.get();
    }
}
