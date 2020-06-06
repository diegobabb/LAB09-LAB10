package com.lab09_lab10.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import com.lab09_lab10.R;
import com.lab09_lab10.logicaNegocio.Curso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterChecked extends RecyclerView.Adapter<MyAdapterChecked.ViewHolder> {

    public List<String> getSeleccionados() {
        return seleccionados;
    }

    public ArrayList<Curso> getmDataset() {
        return mDataset;
    }

    public ArrayList<CheckedTextView> getCheckedTextViews() {
        return checkedTextViews;
    }

    private ArrayList<Curso> mDataset;
    private List<String> seleccionados;
    private ArrayList<CheckedTextView> checkedTextViews;

    public MyAdapterChecked(ArrayList<Curso> mDataset) {
        this.mDataset = mDataset;
        this.seleccionados = new ArrayList<>();
        this.checkedTextViews = new ArrayList<>();
    }

    public void updateList(ArrayList<Curso> cursos) {
        this.mDataset = cursos;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        CheckedTextView checkedTextView;
        View itemView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkedTextView = itemView.findViewById(R.id.checkedTextView);
            this.itemView = itemView;
        }
    }

    @NonNull
    public MyAdapterChecked.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycle_check, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.checkedTextView.setChecked(false);
        viewHolder.checkedTextView.setText(mDataset.get(i).getDescripcion());
        viewHolder.itemView.setOnClickListener(view -> {
            viewHolder.checkedTextView.setChecked(!viewHolder.checkedTextView.isChecked());
            if (viewHolder.checkedTextView.isChecked())
                seleccionados.add(mDataset.get(i).getCodigo());
            else
                seleccionados.remove(mDataset.get(i).getCodigo());
        });

        for (String codigo : seleccionados) {
            if (codigo.trim().equals(mDataset.get(i).getCodigo().trim())) {
                viewHolder.checkedTextView.setChecked(true);
                return;
            }
        }
        checkedTextViews.add(viewHolder.checkedTextView);
    }


    public void setSeleccionados(List<String> seleccionados) {
        this.seleccionados = seleccionados;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
