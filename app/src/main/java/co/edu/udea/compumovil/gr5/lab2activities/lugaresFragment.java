package co.edu.udea.compumovil.gr5.lab2activities;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.LoginFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class lugaresFragment extends Fragment{

    public static List<lugarInfo> places;
    private RecyclerView rv;
    SQLiteDatabase bg=LogIn.db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lugares, container, false);
        rv=(RecyclerView)v.findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        initializeData();
        initializeAdapter();
        getActivity().setTitle("Lugares");

        return v;
    }

    private void initializeData(){
        places=LogIn.dbHelper.getAllPlaces();
  /*      places = new ArrayList<>();

        persons.add(new user("Emma Wilson", "23 years old",R.drawable.emma));
        persons.add(new user("Lavery Maiss", "25 years old", R.drawable.lavery));
        persons.add(new user("Lillie Watts", "35 years old", R.drawable.lillie));*/
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(places);
        rv.setAdapter(adapter);
    }

}
