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
    private Button butRegister;
    private MaterialEditText usernameEt, passEt;

    private String USERNAME,PASS,USERNAME_CALL_BACK,PASS_CALL_BACK,EMAIL_CALL_BACK;
    public DbHelper dbHelper;
    public SQLiteDatabase db;
    //TODO: Pasar la informacion al mainactivity, mediante las propiedades del intent.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        butRegister=(Button) findViewById(R.id.link_signup);
        usernameEt=(MaterialEditText)findViewById(R.id.input_username);
        passEt=(MaterialEditText)findViewById(R.id.input_password);
        dbHelper = new DbHelper(getBaseContext()); //Instancia de DbHelper
        db = dbHelper.getWritableDatabase();
    }

    //TODO: Validar información con la base de datos antes de loggear
    public void logIn(View v) {
        USERNAME=usernameEt.getText().toString();
        PASS=passEt.getText().toString();

        Cursor c=dbHelper.loginQuery(db,USERNAME);
        //Cuando la query retorna algo
            if (c.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registro
                    Log.d("DB", c.getString(0));
                    Log.d("DB", c.getString(1));
                    Log.d("DB", c.getString(2));

                    USERNAME_CALL_BACK=c.getString(0);
                    PASS_CALL_BACK=c.getString(1);
                    EMAIL_CALL_BACK=c.getString(2);

                if(USERNAME.equals(USERNAME_CALL_BACK)&&PASS.equals(PASS_CALL_BACK)){
                    try {
                        Intent intMain = new Intent(LogIn.this, MainActivity.class);
                        intMain.putExtra(user.Column.USER,USERNAME);
                        intMain.putExtra(user.Column.EMAIL,EMAIL_CALL_BACK);
                        startActivity(intMain);
                    } catch (Exception e) {
                        Snackbar.make(findViewById(R.id.login_layout), "Try again later.", Snackbar.LENGTH_LONG)
                                .setAction("action", null).show();
                    }

                }else{
                    Snackbar.make(findViewById(R.id.login_layout), "user/password invalid", Snackbar.LENGTH_LONG)
                            .setAction("action", null).show();
                }

            }


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
