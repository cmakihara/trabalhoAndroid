package com.aula.mobile.aula.sqlite.pesquisaeleitoral.entity;

public class Categoria {

    private int id;
    private String nome;
    private String estado;

    public Categoria(int id, String nome, String estado) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
    }

    public Categoria() {
      
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nome + " - " + estado;
    }
}
