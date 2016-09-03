package co.edu.udea.compumovil.gr5.lab2activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class perfilFragment extends Fragment {
   private TextView tvUsernameProfile, tvEmailProfile;

    public perfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_perfil, container, false);

        tvUsernameProfile = (TextView) v.findViewById(R.id.tv_username_profile);
        tvEmailProfile = (TextView) v.findViewById(R.id.tv_email_profile);
        tvUsernameProfile.setText(getArguments().getString(user.Column.USER));
        tvEmailProfile.setText(getArguments().getString(user.Column.EMAIL));
        return v;

    }

}
