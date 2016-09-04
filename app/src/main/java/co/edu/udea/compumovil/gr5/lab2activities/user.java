package co.edu.udea.compumovil.gr5.lab2activities;

import android.provider.BaseColumns;

public class user {
    String name;
    String age;
    int photoId;
    user(String name, String age, int photoId) {
        this.name = name;
        this.age = age;
        this.photoId = photoId;
    }
    public static final String DB_NAME = "lab2.db"; //Nombre de la DB
    public static final int DB_VERSION = 1; //Versión de la DB
    public static final String TABLE = "users"; //Nombre de la tabla


    public class Column { //Columnas de la tabla
        public static final String ID = BaseColumns._ID; //El ID se suele definir así por convención
        public static final String USER = "user";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
    }
}
