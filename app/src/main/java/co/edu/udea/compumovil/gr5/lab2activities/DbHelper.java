package co.edu.udea.compumovil.gr5.lab2activities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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
        String sqlCreate =String
                .format("create table %s (%s integer primary key AUTOINCREMENT, %s text, %s text, %s text, %s text, %s text, %s text,%s text,%s text)",
                        lugar.TABLE,lugar.Column.ID,
                        lugar.Column.NAME,
                        lugar.Column.DESCRIPTION,
                        lugar.Column.INFO_GENERAL,
                        lugar.Column.TEMP,
                        lugar.Column.LAT,
                        lugar.Column.LONG,
                        lugar.Column.IMAGE,
                        lugar.Column.RATE);


        db.execSQL(sql); //Ejecución de la sentencia
        db.execSQL(sqlCreate);
        loadPlaces(db);



    }
    public void loadPlaces (SQLiteDatabase db){
        ContentValues guatape = new ContentValues();
        guatape.put(lugar.Column.ID,"1");
        guatape.put(lugar.Column.NAME, "Guatapé"); // Contact Name
        guatape.put(lugar.Column.DESCRIPTION,"Antioquia, Colombia");
        guatape.put(lugar.Column.INFO_GENERAL,"Municipio de Colombia, localizado en el oriente Antioqueño que cautiva con su embalse, piedra gigante y fachada de sus casas.");
        guatape.put(lugar.Column.TEMP,"14");
        guatape.put(lugar.Column.LAT,"6.23114");
        guatape.put(lugar.Column.LONG,"-75.15347");
        guatape.put(lugar.Column.IMAGE,String.valueOf(R.drawable.guatape));
        guatape.put(lugar.Column.RATE,"8");
       //
        db.insert(lugar.TABLE,null,guatape);

        ContentValues cano_cristal = new ContentValues();
        cano_cristal.put(lugar.Column.ID,"2");
        cano_cristal.put(lugar.Column.NAME, "Caño Cristales"); // Contact Name
        cano_cristal.put(lugar.Column.DESCRIPTION,"Meta, Colombia");
        cano_cristal.put(lugar.Column.INFO_GENERAL,"Posee el rio de los 5 colores, cercano al municipio la Macarena, es el rio màs hermoso del mundo.Alto turismo");
        cano_cristal.put(lugar.Column.TEMP,"30");
        cano_cristal.put(lugar.Column.LAT,"2.26356");
        cano_cristal.put(lugar.Column.LONG,"-73.79432");
        cano_cristal.put(lugar.Column.IMAGE,String.valueOf(R.drawable.cano_cristal));
        cano_cristal.put(lugar.Column.RATE,"7.5");
        db.insert(lugar.TABLE,null,cano_cristal);

        ContentValues capurgana = new ContentValues();
        capurgana.put(lugar.Column.ID,"3");
        capurgana.put(lugar.Column.NAME, "Capurgana"); // Contact Name
        capurgana.put(lugar.Column.DESCRIPTION,"Chocó, Colombia");
        capurgana.put(lugar.Column.INFO_GENERAL,"Corregimiento del municipio de Acandì, cerca a la frontera Colombo-Panameña,Es una población turistica aislada de carreteras.Solo accesible vìa maritima y aerea.");
        capurgana.put(lugar.Column.TEMP,"27");
        capurgana.put(lugar.Column.LAT,"8.63337");
        capurgana.put(lugar.Column.LONG,"-77.35002");
        capurgana.put(lugar.Column.IMAGE,String.valueOf(R.drawable.capurgana));
        capurgana.put(lugar.Column.RATE,"8.8");
        db.insert(lugar.TABLE,null,capurgana);

        Log.d("DBINFO","Inserting all places");
    }

    public List<lugarInfo> getAllPlaces() {
        List<lugarInfo> placesList = new ArrayList<lugarInfo>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + lugar.TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                lugarInfo place = new lugarInfo();
                place.set_id(Integer.parseInt(cursor.getString(0)));
                place.set_name(cursor.getString(1));
                place.set_description(cursor.getString(2));
                place.set_info_general(cursor.getString(3));
                place.set_temp(Double.parseDouble(cursor.getString(4)));
                place.set_lat(Double.parseDouble(cursor.getString(5)));
                place.set_long(Double.parseDouble(cursor.getString(6)));
                place.set_image(Integer.parseInt(cursor.getString(7)));
                place.set_rate(Double.parseDouble(cursor.getString(8)));

                // Adding contact to list
                placesList.add(place);
            } while (cursor.moveToNext());
        }
        // return contact list
        return placesList;
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

        return db.query(user.TABLE, campos, "user=?", args, null, null, null);
        //Cursor c=db.query(user.TABLE,data,user.Column.USER+"="+username,null,null,null,null);


    }
    // Adding new contact
    public void addPlace(lugarInfo l) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(lugar.Column.NAME, l.get_name()); // Contact Name
        values.put(lugar.Column.DESCRIPTION,l.get_description());
        values.put(lugar.Column.INFO_GENERAL,l.get_info_general());
        values.put(lugar.Column.TEMP,l.get_temp());
        values.put(lugar.Column.RATE,l.get_rate());
        values.put(lugar.Column.LAT,l.get_lat());
        values.put(lugar.Column.LONG,l.get_long());
        values.put(lugar.Column.IMAGE,R.drawable.place_placeholder);

        // Inserting Row
        db.insert(lugar.TABLE, null, values);
    }
    //TODO: Caputar la informacion y convertirlo a un array list para su adaptacion con el ListView



}

