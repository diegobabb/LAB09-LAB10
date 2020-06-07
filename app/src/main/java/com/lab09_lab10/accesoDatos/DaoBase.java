package com.lab09_lab10.accesoDatos;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;

import static android.content.Context.MODE_PRIVATE;

// Clase base para los DAO
public abstract class DaoBase {
    private Context context;
    private static final String DATABASE_NAME = "matriculaDB";
    protected SQLiteDatabase database;

    public DaoBase(Context context) {
        this.context = context;

        database = context.openOrCreateDatabase(
                "matriculadb",
                 MODE_PRIVATE,
                null);

        database.beginTransaction();

        database.execSQL("CREATE TABLE IF NOT EXISTS ESTUDIANTE(" +
                "cedula text PRIMARY KEY, " +
                "nombre text," +
                "apellidos text," +
                "edad integer );");

        database.execSQL("CREATE TABLE IF NOT EXISTS CURSO(" +
                "codigo text PRIMARY KEY, " +
                "descripcion text," +
                "creditos integer );");

        database.execSQL("CREATE TABLE IF NOT EXISTS ESTUDIANTExCURSO(" +
                "id integer PRIMARY KEY autoincrement," +
                "cedula text," +
                "codigo text );");

        database.setTransactionSuccessful(); //commit your changes
        database.endTransaction();
    }

    protected abstract void insertar(String... args);

    protected abstract void eliminar(String... args);

    protected abstract void modificar(String... args);

    protected abstract Cursor listar();

    // Asyncronico
    public void transaccion(Operacion op, String... args) {

        class TransactionTask extends AsyncTask<String, String, Integer> {

             private Operacion op;

             public TransactionTask(Operacion op){
                this.op = op;
             }

             @Override
             protected Integer doInBackground(String... args) {
                 database.beginTransaction();
                 try {
                     switch (op) {
                         case INSERTAR:
                             insertar(args);
                             break;
                         case ELIMINAR:
                             eliminar(args);
                             break;
                         case MODIFICAR:
                             modificar(args);
                             break;
                     }
                     database.setTransactionSuccessful(); //commit your changes
                 } catch (SQLiteException e) {
                     //report problem
                 } finally {
                     database.endTransaction();
                 }
                 return 1;
             }
         }

        TransactionTask task = new TransactionTask(op);
        task.execute(args);
    }

    public Cursor transsaccionCursor(Operacion op, String... args) {
        database.beginTransaction();
        try {
            switch (op) {
                case LISTAR:
                    return listar();
            }
            database.setTransactionSuccessful(); //commit your changes
        } catch (SQLiteException e) {
            //report problem
        } finally {
            database.endTransaction();
        }

        return null;
    }

}


