package com.example.rm30879.crud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.rm30879.crud.adapter.DespesaAdapter;
import com.example.rm30879.crud.dao.DespesaDao;
import com.example.rm30879.crud.model.Despesa;

import java.util.ArrayList;
import java.util.List;

public class ListaActivity extends AppCompatActivity {

    public static int TO_CADASTRO = 10;

    private DespesaDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        dao = new DespesaDao(this);
        RecyclerView lista = (RecyclerView) findViewById(R.id.lista);
        DespesaAdapter adapter = new DespesaAdapter(this, dao.getAll(), dao);
        lista.setLayoutManager(new LinearLayoutManager(this));
        lista.setAdapter(adapter);
    }

    public void cadastrar(View view) {
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }

    public void editar(View view) {
        int id = (int) view.getTag();
        Intent intent = new Intent(this, CadastroActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}
