package com.apktasticos.pocketlife;

/** Modelo: una foto con su título y descripción. */
public class Foto {
    private final int recurso;
    private final String titulo;
    private final String descripcion;

    public Foto(int recurso, String titulo, String descripcion) {
        this.recurso = recurso;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public int getRecurso() { return recurso; }
    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
}
