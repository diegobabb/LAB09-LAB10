package com.lab09_lab10.ui.estudiantes;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.lab09_lab10.accesoDatos.DaoEstudiante;
import com.lab09_lab10.accesoDatos.Operacion;
import com.lab09_lab10.logicaNegocio.Datos;
import com.lab09_lab10.logicaNegocio.Estudiante;


public class EstudiantesViewModel extends AndroidViewModel {

    private DaoEstudiante dao = new DaoEstudiante(getApplication().getApplicationContext());

    public EstudiantesViewModel(@NonNull Application application) {
        super(application);
    }

    public void listar(){
        Datos.getInstance().getEstudiantes().clear();

        Cursor cursor = dao.transsaccionCursor(Operacion.LISTAR);

        int idCedula = cursor.getColumnIndex("cedula");
        int idNombre = cursor.getColumnIndex("nombre");
        int idApellidos = cursor.getColumnIndex("apellidos");
        int idEdad = cursor.getColumnIndex("edad");

        while (cursor.moveToNext()) {
            Datos.getInstance().getEstudiantes().add(new Estudiante(
                    cursor.getString(idCedula),
                    cursor.getString(idNombre),
                    cursor.getString(idApellidos),
                    cursor.getInt(idEdad)
            ));
        }
    }

    public void eliminar(String cedula){
        dao.transaccion(Operacion.ELIMINAR, cedula);
    }
}
