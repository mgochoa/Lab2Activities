package co.edu.udea.compumovil.gr5.lab2activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView placeName;
        TextView placeDescription;
        ImageView placePhoto;
        TextView placeRate;
        ImageButton placeGps,placeInfo;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            placeName = (TextView)itemView.findViewById(R.id.place_name);
            placeDescription = (TextView)itemView.findViewById(R.id.place_description);
            placePhoto = (ImageView)itemView.findViewById(R.id.place_photo);
            placeRate =(TextView)itemView.findViewById(R.id.place_rate);
            placeGps=(ImageButton)itemView.findViewById(R.id.btn_placeGps);
            placeInfo=(ImageButton)itemView.findViewById(R.id.btn_info_place);
        }
    }

    List<lugarInfo> places;

    RVAdapter(List<lugarInfo> places){
        this.places = places;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final PersonViewHolder personViewHolder, int i) {
        personViewHolder.placeName.setText(places.get(i)._name);
        personViewHolder.placeDescription.setText(places.get(i)._description);
        personViewHolder.placeRate.setText(String.valueOf(places.get(i)._rate));
        //personViewHolder.placePhoto.setImageResource(places.get(i)._image);
        Context context =personViewHolder.placePhoto.getContext();
        //Arregla el lag del recylerView

        Picasso.with(context).load(places.get(i)._image).fit().centerCrop().into(personViewHolder.placePhoto);
        //boton para la geolocalizacion
        personViewHolder.placeGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context cont =personViewHolder.placeGps.getContext();
                int position= personViewHolder.getAdapterPosition();
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f",places.get(position)._lat, places.get(position)._long);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                cont.startActivity(intent);
            }
        });
        //Boton para mostrar la informacion
        personViewHolder.placeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position= personViewHolder.getAdapterPosition();
                Context cont =personViewHolder.placeInfo.getContext();
                Intent infoActivity = new Intent(cont, placeInfoActiviy.class);
                Bundle b = new Bundle();
                b.putInt(lugar.Column.IMAGE,places.get(position)._image);
                b.putString(lugar.Column.INFO_GENERAL,places.get(position)._info_general);
                b.putDouble(lugar.Column.TEMP,places.get(position)._temp);
                b.putString(lugar.Column.DESCRIPTION,places.get(position)._description);
                infoActivity.putExtras(b);
                cont.startActivity(infoActivity);

            }
        });
    }

    @Override
    public int getItemCount() {
        return places.size();
    }
}