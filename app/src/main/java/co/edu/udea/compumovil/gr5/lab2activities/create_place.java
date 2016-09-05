package co.edu.udea.compumovil.gr5.lab2activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rengwuxian.materialedittext.MaterialEditText;

public class create_place extends AppCompatActivity {


    MaterialEditText name, description,info_general, rate,temp,lat,_long;
    Button aceptar,cancelar;
    lugarInfo l;
    public create_place() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_place);
        name=(MaterialEditText)findViewById(R.id.input_name);
        description=(MaterialEditText)findViewById(R.id.input_location);
        info_general=(MaterialEditText)findViewById(R.id.input_description);
        rate=(MaterialEditText)findViewById(R.id.input_rate);
        temp=(MaterialEditText)findViewById(R.id.input_temp);
        lat=(MaterialEditText)findViewById(R.id.input_lat);
        _long=(MaterialEditText)findViewById(R.id.input_long);
        aceptar=(Button)findViewById(R.id.btn_aceptar);
        cancelar=(Button)findViewById(R.id.btn_cancelar);

    }

    public void  aceptarLugar(View v){
        l= new lugarInfo();
        l.set_name(name.getText().toString());
        l.set_description(description.getText().toString());
        l.set_info_general(info_general.getText().toString());
        l.set_temp(Double.parseDouble(temp.getText().toString()));
        l.set_lat(Double.parseDouble(lat.getText().toString()));
        l.set_long(Double.parseDouble(_long.getText().toString()));
        l.set_rate(Double.parseDouble(rate.getText().toString()));
        LogIn.dbHelper.addPlace(l);
        lugaresFragment.update();
        finish();

    }
    public void cancelarLugar(View v){
        finish();
    }
}
