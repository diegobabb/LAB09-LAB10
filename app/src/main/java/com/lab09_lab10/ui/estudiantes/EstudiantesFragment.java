package com.lab09_lab10.ui.estudiantes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import androidx.navigation.fragment.NavHostFragment;

import com.lab09_lab10.R;
import com.lab09_lab10.adapters.MyAdapterEstudiantes;
import com.lab09_lab10.logicaNegocio.Datos;
import com.lab09_lab10.logicaNegocio.Estudiante;

import java.util.Objects;

public class EstudiantesFragment extends Fragment implements View.OnClickListener {

    static final String ARG_ESTUDIANTE = "estudiante";
    static final String ARG_POSITION = "position";
    MyAdapterEstudiantes myAdapterEstudiantes;
    FloatingActionButton floatingActionButton;

    @SuppressLint("RestrictedApi")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_estudiantes, container, false);
        final RecyclerView recyclerView = root.findViewById(R.id.recycleView);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(root.getContext());
        layoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        myAdapterEstudiantes = new MyAdapterEstudiantes(Datos.getInstance().getEstudiantes());
        recyclerView.setAdapter(myAdapterEstudiantes);

        MySwipeHelper simpleCallback =
                new MySwipeHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, myAdapterEstudiantes, this);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        floatingActionButton = Objects.requireNonNull(getActivity()).findViewById(R.id.fab);
        floatingActionButton.setVisibility(View.VISIBLE);
        floatingActionButton.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        NavHostFragment.findNavController(EstudiantesFragment.this)
                .navigate(R.id.action_nav_estudiantes_to_EstEdiGuaFragment);
    }

    public void moveToEstEdiGua(Estudiante estudiante, int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EstudiantesFragment.ARG_ESTUDIANTE, estudiante);
        bundle.putInt(EstudiantesFragment.ARG_POSITION, position);
        NavHostFragment.findNavController(EstudiantesFragment.this)
                .navigate(R.id.action_nav_estudiantes_to_EstEdiGuaFragment);
    }

    public void removeEstudiante(int position, Estudiante aux) {
        myAdapterEstudiantes.getmDataset().add(position, aux);
        Datos.getInstance().getEstudiantes().add(position, aux);
        myAdapterEstudiantes.notifyItemInserted(position);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onResume() {
        super.onResume();
        setHasOptionsMenu(true);
        floatingActionButton.setVisibility(View.VISIBLE);
        myAdapterEstudiantes.notifyDataSetChanged();
    }
}
