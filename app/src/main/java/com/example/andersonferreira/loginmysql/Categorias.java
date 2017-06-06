package com.example.andersonferreira.loginmysql;

import java.io.Serializable;

public class Categorias implements Serializable {

    public String id;
    public String nome;

    public String toString(){
        String lista = id;
        return lista;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
