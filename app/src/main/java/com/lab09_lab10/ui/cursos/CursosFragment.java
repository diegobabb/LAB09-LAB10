package com.lab09_lab10.ui.cursos;

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
import com.lab09_lab10.adapters.MyAdapterCursos;
import com.lab09_lab10.logicaNegocio.Curso;
import com.lab09_lab10.logicaNegocio.Datos;

import java.util.Objects;

public class CursosFragment extends Fragment implements View.OnClickListener {

    static final String ARG_CURSO = "curso";
    static final String ARG_POSITION = "position";
    MyAdapterCursos myAdapterCursos;
    FloatingActionButton floatingActionButton;

    @SuppressLint("RestrictedApi")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cursos, container, false);
        final RecyclerView recyclerView = root.findViewById(R.id.recycleView);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(root.getContext());
        layoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        myAdapterCursos = new MyAdapterCursos(Datos.getInstance().getCursos());
        recyclerView.setAdapter(myAdapterCursos);

        MySwipeHelper simpleCallback =
                new MySwipeHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, myAdapterCursos, this);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        floatingActionButton = Objects.requireNonNull(getActivity()).findViewById(R.id.fab);
        floatingActionButton.setVisibility(View.VISIBLE);
        floatingActionButton.setOnClickListener(this);
        return root;
    }

    public void moveToCurEdiGua(Curso curso, int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(CursosFragment.ARG_CURSO, curso);
        bundle.putInt(CursosFragment.ARG_POSITION, position);
        NavHostFragment.findNavController(CursosFragment.this)
                .navigate(R.id.action_nav_estudiantes_to_EstEdiGuaFragment);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onResume() {
        super.onResume();
        setHasOptionsMenu(true);
        floatingActionButton.setVisibility(View.VISIBLE);
        myAdapterCursos.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        NavHostFragment.findNavController(CursosFragment.this)
                .navigate(R.id.action_nav_cursos_to_CurEdiGuaFragment);
    }
}
