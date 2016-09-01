package co.edu.udea.compumovil.gr5.lab2activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.rengwuxian.materialedittext.MaterialEditText;

public class LogIn extends AppCompatActivity {
    Button butRegister;
    MaterialEditText usernameEt, passEt;

    //TODO: Pasar la informacion al mainactivity, mediante las propiedades del intent.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        butRegister=(Button) findViewById(R.id.link_signup);
        usernameEt=(MaterialEditText)findViewById(R.id.input_username);
        passEt=(MaterialEditText)findViewById(R.id.input_password);
    }

    //TODO: Validar información con la base de datos antes de loggear
    public void logIn(View v) {
        DbHelper dbHelper = new DbHelper(this); //Instancia de DbHelper
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c=dbHelper.loginQuery(db,usernameEt.getText().toString(),passEt.getText().toString());
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Log.d("DB",c.getString(0));
                Log.d("DB",c.getString(1));
            } while(c.moveToNext());
        }



        /*try {
            Intent intMain = new Intent(LogIn.this, MainActivity.class);
            startActivity(intMain);
        } catch (Exception e) {
            Snackbar.make(findViewById(R.id.login_layout), "Try again later.", Snackbar.LENGTH_LONG)
                    .setAction("action", null).show();
        }*/
    }

    public void registerFunc(View v){
        try {
            Intent intRegister = new Intent(LogIn.this, register.class);
            startActivity(intRegister);
        }catch(Exception e){
            Snackbar.make(findViewById(R.id.login_layout), "Try again later.", Snackbar.LENGTH_LONG)
                    .setAction("action", null).show();
        }
    }

}
