package co.edu.udea.compumovil.gr5.lab2activities;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class register extends AppCompatActivity {


    EditText username, email, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username=(EditText)findViewById(R.id.input_username_register);
        email=(EditText)findViewById(R.id.input_email_register);
        pass=(EditText)findViewById(R.id.input_password_register);
    }

    //Guarda la informacion en la base de datos
    public void saveInfo(View v){
        DbHelper dbHelper = new DbHelper(this); //Instancia de DbHelper
        SQLiteDatabase db = dbHelper.getWritableDatabase();
         //Obtener instancia de la BD
        ContentValues values = new ContentValues();
        //values.put(user.Column.ID,); //Se pasan pares nombre-valor
        values.put(user.Column.USER,
                username.getText().toString());
        values.put(user.Column.EMAIL,
                email.getText().toString());
        values.put(user.Column.PASSWORD, pass.getText().toString());
        db.insertWithOnConflict(user.TABLE, null, values,
                SQLiteDatabase.CONFLICT_IGNORE);
        finish();//Se guarda la fila en la base de datos


    }
}
