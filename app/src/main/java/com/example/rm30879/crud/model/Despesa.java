package com.example.rm30879.crud.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Despesa {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public final int id;
    public final String nome;
    public final String categoria;
    public final Date data;
    public final float valor;

    public Despesa(String nome, String categoria, String data, float valor) throws ParseException {
        this.id = 0;
        this.nome = nome;
        this.categoria = categoria;
        this.valor = valor;
        this.data = formatter.parse(data);
    }

    public Despesa(int id, String nome, String categoria, long data, float valor) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.data = new Date(data);
        this.valor = valor;
    }

    public Despesa(int id, String nome, String categoria, String data, float valor) throws ParseException {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.data = formatter.parse(data);
        this.valor = valor;
    }

    public String formatarData() {
        return formatter.format(data);
    }
}
