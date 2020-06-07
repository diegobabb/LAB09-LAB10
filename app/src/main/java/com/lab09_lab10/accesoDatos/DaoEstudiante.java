package com.lab09_lab10.accesoDatos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.lab09_lab10.MainActivity;
import com.lab09_lab10.logicaNegocio.Datos;
import com.lab09_lab10.logicaNegocio.Estudiante;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DaoEstudiante extends DaoBase {

    public DaoEstudiante(Context context) {
        super(context);
    }

    @Override
    protected void insertar(String... args) {
        database.beginTransaction();

        String pre_sql = "insert into ESTUDIANTE(cedula, nombre, apellidos, edad)"
                + " values('%s', '%s', '%s', %d);";

        @SuppressLint("DefaultLocale") String sql = String.format(pre_sql, args[0], args[1], args[2], Integer.parseInt(args[3]));

        try {
            database.execSQL(sql);
        } catch (SQLException sqle) {
            Log.d("ERROR", Objects.requireNonNull(sqle.getMessage()));
        }

        database.setTransactionSuccessful(); //commit your changes
        database.endTransaction();
    }

    @Override
    protected void eliminar(String... args) {
        String pre_sql = "DELETE FROM ESTUDIANTE WHERE cedula = '%s'";
        String pre_sql_matriculados = "DELETE FROM ESTUDIANTExCURSO WHERE cedula = '%s'";

        String sql = String.format(pre_sql, args[0]);
        String sql_eliminar_matriculados = String.format(pre_sql, args[0]);

        try {
            database.execSQL(sql);
            database.execSQL(sql_eliminar_matriculados);
        } catch (SQLException sqle) {
            Log.d("ERROR ELIMINANDO", Objects.requireNonNull(sqle.getMessage()));
        }
    }

    @Override
    protected void modificar(String... args) {
        String pre_sql = "UPDATE ESTUDIANTE SET nombre = '%s', apellidos = '%s', edad = %d WHERE cedula = '%s'";

        @SuppressLint("DefaultLocale") String sql = String.format(pre_sql, args[1], args[2], Integer.parseInt(args[3]), args[0]);

        try {
            database.execSQL(sql);
        } catch (SQLException sqle) {
            Log.e("Error", Objects.requireNonNull(sqle.getMessage()));
        }
    }

    @Override
    protected Cursor listar(){
        String mySQL = "SELECT cedula, nombre, apellidos, edad FROM ESTUDIANTE ";
        return database.rawQuery(mySQL, null);
    }

    public void asignarEstudianteCursos(String cedula, List<String> cursos) {

        String prepare_eliminar = "DELETE FROM ESTUDIANTExCURSO WHERE cedula = '%s'";
        String sql_eliminar = String.format(prepare_eliminar, cedula);

        String prepare_insertar = "INSERT INTO ESTUDIANTExCURSO (cedula, codigo) values ('%s', '%s'); ";
        String sql_insertar = "";

        try {
            database.execSQL(sql_eliminar);

            for (String codigo : cursos) {
                sql_insertar = String.format(prepare_insertar, cedula, codigo);
                database.execSQL(sql_insertar);
            }

        } catch (SQLException sqle) {
            Log.d("ERROR ELIMINANDO", Objects.requireNonNull(sqle.getMessage()));
        }
    }

}
