package com.example.rm30879.crud.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.rm30879.crud.R;
import com.example.rm30879.crud.dao.DespesaDao;
import com.example.rm30879.crud.model.Despesa;

import java.util.List;

public class DespesaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final List<Despesa> despesas;
    private final DespesaDao dao;

    public DespesaAdapter(Context context, List<Despesa> despesas, DespesaDao dao) {
        this.context = context;
        this.despesas = despesas;
        this.dao = dao;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.despesa, parent, false);
        return new DespesaHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DespesaHolder itemHolder = (DespesaHolder) holder;
        Despesa despesa = despesas.get(position);

        itemHolder.nome.setText(despesa.nome);
        itemHolder.categoria.setText(despesa.categoria);
        itemHolder.data.setText(despesa.formatarData());
        itemHolder.valor.setText(String.valueOf(despesa.valor));
        itemHolder.editar.setTag(despesa.id);
        itemHolder.excluir.setOnClickListener(new Excluir(position));
    }

    @Override
    public int getItemCount() {
        return despesas.size();
    }

    private class DespesaHolder extends RecyclerView.ViewHolder {
        private final TextView nome;
        private final TextView categoria;
        private final TextView data;
        private final TextView valor;
        private final Button editar;
        private final Button excluir;

        DespesaHolder(View itemView) {
            super(itemView);

            nome = (TextView) itemView.findViewById(R.id.nome);
            categoria = (TextView) itemView.findViewById(R.id.categoria);
            data = (TextView) itemView.findViewById(R.id.data);
            valor = (TextView) itemView.findViewById(R.id.valor);
            editar = (Button) itemView.findViewById(R.id.editar);
            excluir = (Button) itemView.findViewById(R.id.excluir);
        }
    }

    private class Excluir implements View.OnClickListener {

        private final int position;

        public Excluir(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            dao.delete(despesas.get(position).id);
            despesas.remove(position);
            notifyDataSetChanged();
        }
    }
}
