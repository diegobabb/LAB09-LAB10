package com.lab09_lab10.accesoDatos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;
import java.util.Objects;

public class DaoCurso extends DaoBase {

    public DaoCurso(Context context) {
        super(context);
    }

    @Override
    protected void insertar(String... args) {
        database.beginTransaction();

        String pre_sql = "insert into CURSO(codigo, descripcion, creditos)"
                + " values('%s', '%s', %d);";

        @SuppressLint("DefaultLocale") String sql = String.format(pre_sql, args[0], args[1], Integer.parseInt(args[2]));

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
        database.beginTransaction();

        String pre_sql = "DELETE FROM CURSO WHERE codigo = '%s'";
        String sql = String.format(pre_sql, args[0]);

        String pre_sql_2 = "DELETE FROM ESTUDIANTExCURSO WHERE codigo = '%s'";
        String pre_sql_query = String.format(pre_sql_2, args[0]);

        try {
            database.execSQL(pre_sql_query);
            database.execSQL(sql);
        } catch (SQLException sqle) {
            Log.d("ERROR ELIMINANDO", Objects.requireNonNull(sqle.getMessage()));
        }

        database.setTransactionSuccessful(); //commit your changes
        database.endTransaction();
    }

    @Override
    protected void modificar(String... args) {
        database.beginTransaction();

        String pre_sql = "UPDATE CURSO SET descripcion = '%s', creditos = %d WHERE codigo = '%s'";

        @SuppressLint("DefaultLocale") String sql = String.format(pre_sql, args[1], Integer.parseInt(args[2]), args[0]);

        try {
            database.execSQL(sql);
        } catch (SQLException sqle) {
            Log.e("Error", Objects.requireNonNull(sqle.getMessage()));
        }

        database.setTransactionSuccessful(); //commit your changes
        database.endTransaction();
    }

    @Override
    protected Cursor listar(){
        String mySQL = "SELECT codigo, descripcion, creditos FROM CURSO";
        return database.rawQuery(mySQL, null);
    }

    public Cursor listarPorEstudiante(String cedula){
        String PREmySQL =
                "SELECT  ESTUDIANTExCURSO.codigo AS cod " +
                        "FROM ESTUDIANTExCURSO " +
                        "INNER JOIN ESTUDIANTE ON ESTUDIANTExCURSO.cedula = ESTUDIANTE.cedula " +
                        "INNER JOIN CURSO ON ESTUDIANTExCURSO.codigo = CURSO.codigo " +
                        "WHERE ESTUDIANTExCURSO.cedula = '%s'";

        String mySQL = String.format(PREmySQL, cedula);

        return  database.rawQuery(mySQL, null);
    }

}
