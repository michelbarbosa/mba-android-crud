package com.example.rm30879.crud.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rm30879.crud.model.Despesa;

import java.util.ArrayList;
import java.util.List;

public class DespesaDao {
    private DbOpenHelper banco;

    public DespesaDao(Context context) {
        banco = new DbOpenHelper(context);
    }

    public void save(Despesa despesa) {
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", despesa.nome);
        values.put("categoria", despesa.categoria);
        values.put("data", despesa.data.getTime());
        values.put("valor", despesa.valor);
        if (despesa.id > 0) {
            db.update("despesas", values, "id = ?", new String[]{ String.valueOf(despesa.id) });
            db.close();
            return;
        }
        long resultado = db.insert("despesas", null, values);
        db.close();
        if (resultado == -1) {
            throw new RuntimeException("Erro ao inserir despesa");
        }
    }

    public void delete(int id) {
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete("despesas", "id = ?", new String[]{ String.valueOf(id) });
        db.close();
    }

    public List<Despesa> getAll() {
        List<Despesa> despesas = new ArrayList<>();
        final String sql = "select id, nome, categoria, data, valor from despesas order by data desc";
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String nome = cursor.getString(1);
                String categoria = cursor.getString(2);
                long data = cursor.getLong(3);
                float valor = cursor.getFloat(4);
                Despesa despesa = new Despesa(id, nome, categoria, data, valor);
                despesas.add(despesa);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return despesas;
    }

    public Despesa getById(int id) {
        SQLiteDatabase db = banco.getReadableDatabase();
        String colunas[] = { "nome", "categoria", "data", "valor" };
        Cursor cursor = db.query(true, "despesas", colunas, "id = ?", new String[]{ String.valueOf(id) }, null, null, null, null);
        if (cursor == null) {
            return null;
        }
        cursor.moveToFirst();
        String nome = cursor.getString(cursor.getColumnIndex("nome"));
        String categoria = cursor.getString(cursor.getColumnIndex("categoria"));
        long data = cursor.getLong(cursor.getColumnIndex("data"));
        float valor = cursor.getFloat(cursor.getColumnIndex("valor"));
        return new Despesa(id, nome, categoria, data, valor);
    }
}
