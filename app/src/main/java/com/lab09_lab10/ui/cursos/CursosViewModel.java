package com.lab09_lab10.ui.cursos;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.lab09_lab10.accesoDatos.DaoCurso;
import com.lab09_lab10.accesoDatos.DaoEstudiante;
import com.lab09_lab10.accesoDatos.Operacion;
import com.lab09_lab10.logicaNegocio.Curso;
import com.lab09_lab10.logicaNegocio.Datos;
import com.lab09_lab10.logicaNegocio.Estudiante;

public class CursosViewModel extends AndroidViewModel {
    private DaoCurso dao = new DaoCurso(getApplication().getApplicationContext());

    public CursosViewModel(@NonNull Application application) {
        super(application);
    }

    public void listar(){
        Datos.getInstance().getCursos().clear();

        Cursor cursor = dao.transsaccionCursor(Operacion.LISTAR);

        int idCodigo = cursor.getColumnIndex("codigo");
        int idDescrip = cursor.getColumnIndex("descripcion");
        int idCreditos = cursor.getColumnIndex("creditos");

        while (cursor.moveToNext()) {
            Datos.getInstance().getCursos().add(new Curso(
                    cursor.getString(idCodigo),
                    cursor.getString(idDescrip),
                    cursor.getInt(idCreditos)
            ));
        }
    }

    public void eliminar(String codigo){
        dao.transaccion(Operacion.ELIMINAR, codigo);
    }
}
