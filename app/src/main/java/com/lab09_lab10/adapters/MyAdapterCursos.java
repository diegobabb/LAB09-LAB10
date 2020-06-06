package com.lab09_lab10.adapters;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lab09_lab10.R;
import com.lab09_lab10.logicaNegocio.Curso;

import java.util.ArrayList;

public class MyAdapterCursos extends RecyclerView.Adapter<MyAdapterCursos.ViewHolder>{

    private ArrayList<Curso> mDataset;

    public ArrayList<Curso> getmDataset() {
        return mDataset;
    }

    public MyAdapterCursos(ArrayList<Curso> mDataset) {
        this.mDataset = mDataset;
    }

    @NonNull
    @Override
    public MyAdapterCursos.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycle, viewGroup, false));
    }

    public void updateList(ArrayList<Curso> cursos) {
        this.mDataset = cursos;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView descripcion;
        TextView creditos;
        TextView codigo;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            descripcion = itemView.findViewById(R.id.nombre);
            creditos = itemView.findViewById(R.id.cedula);
            codigo = itemView.findViewById(R.id.edad);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyAdapterCursos.ViewHolder viewHolder, int i) {
        Curso curso = mDataset.get(i);
        viewHolder.descripcion.setText(curso.getDescripcion());
        viewHolder.creditos.setText(curso.getCreditos());
        viewHolder.codigo.setText(curso.getCodigo());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
