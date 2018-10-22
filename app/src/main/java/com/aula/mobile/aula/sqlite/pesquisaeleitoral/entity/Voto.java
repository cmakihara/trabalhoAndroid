package com.aula.mobile.aula.sqlite.pesquisaeleitoral.entity;

public class Voto {


    private int id;

    private String nome;

    private String categoria;

    private int idCategoria;

    private String estado;

    private int voto;

    public Voto(int id, String nome, String categoria, int idCategoria, String estado, int voto) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.idCategoria = idCategoria;
        this.estado = estado;
        this.voto = voto;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }

    @Override
    public String toString() {
        return "Candidato: " + nome + " " + " Votos:  " + voto;
    }
}
