package org.dominio;

public class Cliente {
    private Long id;
    private String nome;
    private String telfone;
    private String morada;

    public Cliente() {
    }

    public Cliente(Long id, String nome, String telfone, String morada) {
        this.id = id;
        this.nome = nome;
        this.telfone = telfone;
        this.morada = morada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelfone() {
        return telfone;
    }

    public void setTelfone(String telfone) {
        this.telfone = telfone;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }
}
