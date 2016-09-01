package co.edu.udea.compumovil.gr5.lab2activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LogIn extends AppCompatActivity {
    Button butRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        butRegister=(Button) findViewById(R.id.link_signup);
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
