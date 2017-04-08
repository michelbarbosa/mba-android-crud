package com.example.rm30879.crud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rm30879.crud.dao.DespesaDao;
import com.example.rm30879.crud.model.Despesa;

import java.text.ParseException;

public class CadastroActivity extends AppCompatActivity {

    private EditText campoNome;
    private Spinner campoCategoria;
    private EditText campoData;
    private EditText campoValor;
    private DespesaDao dao;
    private Integer id = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        dao = new DespesaDao(this);
        campoNome = (EditText) findViewById(R.id.nome);
        campoCategoria = (Spinner) findViewById(R.id.categoria);
        campoData = (EditText) findViewById(R.id.data);
        campoValor = (EditText) findViewById(R.id.valor);

        int id = getIntent().getIntExtra("id", -1);
        if (id == -1) {
            return;
        }
        this.id = id;
        Despesa despesa = dao.getById(id);
        campoNome.setText(despesa.nome);
        campoCategoria.setSelection(((ArrayAdapter)campoCategoria.getAdapter()).getPosition(despesa.categoria));
        campoData.setText(despesa.formatarData());
        campoValor.setText(String.valueOf(despesa.valor));
    }

    public void salvar(View view) {
        try {
            Despesa despesa = criarDespesa();
            dao.save(despesa);
            goToLista();
        } catch (ParseException e) {
            Toast.makeText(this, "Data inválida", Toast.LENGTH_LONG).show();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Valor inválido", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        goToLista();
    }

    private void goToLista() {
        Intent intent = new Intent(this, ListaActivity.class);
        startActivity(intent);
    }

    private Despesa criarDespesa() throws ParseException {
        String nome = campoNome.getText().toString();
        String categoria = campoCategoria.getSelectedItem().toString();
        String data = campoData.getText().toString();
        float valor = Float.parseFloat(campoValor.getText().toString());

        if (id == null) {
            return new Despesa(nome, categoria, data, valor);
        }
        return new Despesa(id, nome, categoria, data, valor);
    }
}
