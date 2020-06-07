package com.lab09_lab10.ui.estEdiGua;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.lab09_lab10.R;
import com.lab09_lab10.accesoDatos.DaoCurso;
import com.lab09_lab10.accesoDatos.DaoEstudiante;
import com.lab09_lab10.accesoDatos.Operacion;
import com.lab09_lab10.logicaNegocio.Curso;
import com.lab09_lab10.logicaNegocio.Datos;
import com.lab09_lab10.logicaNegocio.Estudiante;

import java.util.ArrayList;
import java.util.List;

public class EstEdiGuaViewModel extends AndroidViewModel {

    private DaoEstudiante dao = new DaoEstudiante(getApplication().getApplicationContext());

    private MutableLiveData<EstEdiGuaFormState> estEdiGuaFormState = new MutableLiveData<>();

    public EstEdiGuaViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<EstEdiGuaFormState> getEstEdiGuaFormState() {
        return estEdiGuaFormState;
    }

    public void dataChanged(@Nullable String cedula, @Nullable String nombre, @Nullable String apellidos, @Nullable String edad) {
        EstEdiGuaFormState formState = new EstEdiGuaFormState();
        boolean dataValid = true;
        if (isTextInvalid(nombre)) {
            formState.setNombreError(R.string.invalid_username);
            dataValid = false;
        }
        if (isTextInvalid(apellidos)) {
            formState.setApellidosError(R.string.invalid_username);
            dataValid = false;
        }
        if (isCedulaInvalid(cedula)) {
            formState.setCedulaError(R.string.invalid_username);
            dataValid = false;
        }
        if (isTextInvalid(edad)) {
            formState.setEdadError(R.string.invalid_username);
            dataValid = false;
        }
        formState.setDataValid(dataValid);
        estEdiGuaFormState.setValue(formState);
    }

    private boolean isTextInvalid(String username) {
        if (username == null)
            return true;
        return username.trim().isEmpty();
    }

    private boolean isCedulaInvalid(String cedula){
        if (cedula == null)
            return true;
        return cedula.length() != 9;
    }

    public void insertarEstudiante(Estudiante estudiante){
        dao.transaccion(Operacion.INSERTAR, estudiante.getCedula(), estudiante.getNombre(),
                estudiante.getApellidos(), String.valueOf(estudiante.getEdad()));
    }

    public void modificarEstudiante(Estudiante estudiante){
        dao.transaccion(Operacion.MODIFICAR,estudiante.getCedula(), estudiante.getNombre(),
                estudiante.getApellidos(), String.valueOf(estudiante.getEdad()));
    }

    public void listar(){
        DaoCurso daocurso = new DaoCurso(getApplication().getApplicationContext());

        Datos.getInstance().getCursos().clear();

        Cursor cursor = daocurso.transsaccionCursor(Operacion.LISTAR);

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

    public void  asignarEstudianteCursos(Estudiante estudiante, List<String> cursos){
        dao.asignarEstudianteCursos(estudiante.getCedula(), cursos);
    }

    public  ArrayList<String>  getCursosEstudiante(Estudiante estudiante){
        DaoCurso daocurso = new DaoCurso(getApplication().getApplicationContext());

        Cursor cursor = daocurso.listarPorEstudiante(estudiante.getCedula());

        int cod_indice = cursor.getColumnIndex("cod");
        ArrayList<String> seleccionados = new ArrayList<>();

        while (cursor.moveToNext()) {
            seleccionados.add(cursor.getString(cod_indice));
        }

        return  seleccionados;
    }


}
