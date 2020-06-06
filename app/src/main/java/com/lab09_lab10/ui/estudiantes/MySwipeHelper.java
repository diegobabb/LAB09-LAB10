package com.lab09_lab10.ui.estudiantes;

import android.annotation.SuppressLint;
import android.database.SQLException;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.lab09_lab10.MainActivity;
import com.lab09_lab10.R;
import com.lab09_lab10.adapters.MyAdapterEstudiantes;
import com.lab09_lab10.logicaNegocio.Datos;
import com.lab09_lab10.logicaNegocio.Estudiante;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class MySwipeHelper extends ItemTouchHelper.SimpleCallback {

    private MyAdapterEstudiantes adapter;
    private EstudiantesFragment fragment;

    public MySwipeHelper(int dragDirs, int swipeDirs, MyAdapterEstudiantes adapter, EstudiantesFragment fragment) {
        super(dragDirs, swipeDirs);
        this.adapter = adapter;
        this.fragment = fragment;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                          @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        final int position = viewHolder.getAdapterPosition();
        switch (direction) {
            case ItemTouchHelper.LEFT:
                final Estudiante aux = adapter.getmDataset().remove(position);
                eliminarEstudiante(aux, position);
                break;
            case ItemTouchHelper.RIGHT:
                fragment.moveToEstEdiGua(adapter.getmDataset().get(position), position);
                break;
        }
    }

    @SuppressLint("ResourceType")
    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                            @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState,
                            boolean isCurrentlyActive) {
        new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                .addSwipeLeftBackgroundColor(Color.parseColor(fragment.getString(R.color.delete)))
                .addSwipeLeftActionIcon(R.drawable.ic_delete)
                .addSwipeRightBackgroundColor(Color.parseColor(fragment.getString(R.color.edit)))
                .addSwipeRightActionIcon(R.drawable.ic_edit).create().decorate();
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }


    private void eliminarEstudiante(Estudiante estudiante, int position) {
        String pre_sql = "DELETE FROM ESTUDIANTE WHERE cedula = '%s'";
        String pre_sql_matriculados = "DELETE FROM ESTUDIANTExCURSO WHERE cedula = '%s'";

        String sql = String.format(pre_sql, estudiante.getCedula());
        String sql_eliminar_matriculados = String.format(pre_sql, estudiante.getCedula());

        try {
            MainActivity.db.execSQL(sql);
            MainActivity.db.execSQL(sql_eliminar_matriculados);
            Datos.getInstance().getEstudiantes().remove(estudiante);
            adapter.notifyItemRemoved(position);
        } catch (SQLException sqle) {
            Log.d("ERROR ELIMINANDO", sqle.getMessage());
        }
    }

}