package co.edu.udea.compumovil.gr5.lab2activities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mguillermo.ochoa on 31/08/16.
 */
public class DbHelper extends SQLiteOpenHelper { //
    private static final String TAG = DbHelper.class.getSimpleName();

    public DbHelper(Context context) {
        super(context, user.DB_NAME, null, user.DB_VERSION);
        //
    }

    //Se llama solamente cuando se crea la BD por primer vez
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = String
                .format("create table %s (%s int primary key, %s text, %s text, %s text)",
                        user.TABLE, user.Column.ID,
                        user.Column.USER,
                        user.Column.EMAIL,
                        user.Column.PASSWORD);
        //Sentencia para crear tabla
        Log.d(TAG, "onCreate with SQL: " + sql);
        db.execSQL(sql); //Ejecución de la sentencia
    }

    //Se llama cada que el schema cambie (nueva versión)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       /* Por simplicidad se borran los datos y se crea la tabla de nuevo. Usualmente se haría un ALTER TABLE para cambiar la estructura de la base de datos sin borrar los existentes */
        db.execSQL("drop table if exists " + user.TABLE); //Borrar tabla
        onCreate(db);//Crear tabla de nuevo
    }
}

