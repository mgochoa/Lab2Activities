package co.edu.udea.compumovil.gr5.lab2activities;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class placeInfoActiviy extends AppCompatActivity {
    ImageView placeImageInfo;
    TextView placeDescriptionInfo,placeLocationInfo,placeTempInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_info_activiy);
        placeImageInfo=(ImageView)findViewById(R.id.place_photo_info);
        placeDescriptionInfo=(TextView)findViewById(R.id.place_info_general);
        placeTempInfo=(TextView)findViewById(R.id.place_temp_info);
        placeLocationInfo=(TextView)findViewById(R.id.place_location_info);
        Bundle b = this.getIntent().getExtras();

        if (b != null) {
            Picasso.with(getBaseContext()).load(b.getInt(lugar.Column.IMAGE)).fit().centerCrop().into(placeImageInfo);
            placeDescriptionInfo.setText(b.getString(lugar.Column.INFO_GENERAL));
            placeTempInfo.setText(" "+(b.getDouble(lugar.Column.TEMP))+" °C");
            placeLocationInfo.setText(" "+b.getString(lugar.Column.DESCRIPTION));
        }
    }
}
