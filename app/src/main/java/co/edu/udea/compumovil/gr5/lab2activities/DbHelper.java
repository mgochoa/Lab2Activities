package co.edu.udea.compumovil.gr5.lab2activities;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mguillermo.ochoa on 31/08/16.
 */
public class DbHelper extends SQLiteOpenHelper { //
    private static final String TAG = "DbOnCreate";

    public DbHelper(Context context) {
        super(context, user.DB_NAME, null, user.DB_VERSION);
        //
    }

    //Se llama solamente cuando se crea la BD por primer vez
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = String
                .format("create table %s (%s integer primary key AUTOINCREMENT, %s text, %s text, %s text)",
                        user.TABLE, user.Column.ID,
                        user.Column.USER,
                        user.Column.EMAIL,
                        user.Column.PASSWORD);
        //Sentencia para crear tabla
        Log.d(TAG, "onCreate with SQL: " + sql);
        String sql2 =String
                .format("create table %s (%s integer primary key AUTOINCREMENT, %s text, %s text, %s text, %s text, %s text, %s text,%s text)",
                        lugar.TABLE,lugar.Column.ID,
                        lugar.Column.NAME,
                        lugar.Column.DESCRIPTION,
                        lugar.Column.INFO_GENERAL,
                        lugar.Column.TEMP,
                        lugar.Column.LAT,
                        lugar.Column.LONG,
                        lugar.Column.IMAGE);
        Log.d(TAG, "onCreate with SQL: " + sql2);
        db.execSQL(sql); //Ejecución de la sentencia
        db.execSQL(sql2);


    }

    //Se llama cada que el schema cambie (nueva versión)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       /* Por simplicidad se borran los datos y se crea la tabla de nuevo. Usualmente se haría un ALTER TABLE para cambiar la estructura de la base de datos sin borrar los existentes */
        db.execSQL("drop table if exists " + user.TABLE);
        db.execSQL("drop table if exists " + lugar.TABLE);//Borrar tabla
        onCreate(db);//Crear tabla de nuevo
    }

    public Cursor loginQuery(SQLiteDatabase db, String username){
        String[] campos = new String[] {user.Column.USER,user.Column.PASSWORD,user.Column.EMAIL};
        String[] args = new String[] {username};

        Cursor c = db.query(user.TABLE, campos, "user=?", args, null, null, null);
        //Cursor c=db.query(user.TABLE,data,user.Column.USER+"="+username,null,null,null,null);
        return c;

    }
    //TODO: Caputar la informacion y convertirlo a un array list para su adaptacion con el ListView
    public Cursor placesQuery(SQLiteDatabase db){
        Cursor c =db.query(lugar.TABLE,null,null,null,null,null,null);
        return c;
    }


}

