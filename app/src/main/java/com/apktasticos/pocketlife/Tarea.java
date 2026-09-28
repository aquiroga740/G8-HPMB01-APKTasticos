package com.apktasticos.pocketlife;

/** Modelo: una tarea del gestor. */
public class Tarea {
    private final int id;
    private final String titulo;
    private boolean completada;

    public Tarea(int id, String titulo) {
        this.id = id;
        this.titulo = titulo;
        this.completada = false;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public boolean isCompletada() { return completada; }
    public void setCompletada(boolean completada) { this.completada = completada; }
    public void marcarCompletada() { this.completada = true; }
}
