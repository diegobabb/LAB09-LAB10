package com.lab09_lab10.adapters;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lab09_lab10.R;
import com.lab09_lab10.logicaNegocio.Estudiante;

import java.util.ArrayList;

public class MyAdapterEstudiantes extends RecyclerView.Adapter<MyAdapterEstudiantes.ViewHolder> {

    private ArrayList<Estudiante> mDataset;

    public ArrayList<Estudiante> getmDataset() {
        return mDataset;
    }

    public MyAdapterEstudiantes(ArrayList<Estudiante> mDataset) {
        this.mDataset = mDataset;
    }

    @NonNull
    @Override
    public MyAdapterEstudiantes.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycle, viewGroup, false));
    }

    public void updateList(ArrayList<Estudiante> estudiantes) {
        this.mDataset = estudiantes;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre;
        TextView cedula;
        TextView edad;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            cedula = itemView.findViewById(R.id.cedula);
            edad = itemView.findViewById(R.id.edad);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyAdapterEstudiantes.ViewHolder viewHolder, int i) {
        Estudiante estudiante = mDataset.get(i);
        viewHolder.nombre.setText(estudiante.getNombre() + " " + estudiante.getApellidos());
        viewHolder.cedula.setText(estudiante.getCedula());
        viewHolder.edad.setText(estudiante.getEdad().toString());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
