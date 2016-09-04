package co.edu.udea.compumovil.gr5.lab2activities;

import android.provider.BaseColumns;

/**
 * Created by mguillermo.ochoa on 1/09/16.
 */
public class lugar {
    public static final String DB_NAME = "lab2.db"; //Nombre de la DB
    public static final int DB_VERSION = 1; //Versión de la DB
    public static final String TABLE = "places"; //Nombre de la tabla

    public class Column { //Columnas de la tabla
        public static final String ID = BaseColumns._ID; //El ID se suele definir así por convención
        public static final String NAME= "name";
        public static final String DESCRIPTION = "description";
        public static final String INFO_GENERAL = "info_general";
        public static final String TEMP = "temp";
        public static final String LAT = "lat";
        public static final String LONG = "long";
        public static final String IMAGE= "image";
        public static final String RATE= "rate";


    }
}
