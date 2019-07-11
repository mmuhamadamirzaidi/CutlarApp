package com.mmuhamadamirzaidi.cutlarapp.Fragments;


import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.accountkit.AccountKit;
import com.mmuhamadamirzaidi.cutlarapp.BookingActivity;
import com.mmuhamadamirzaidi.cutlarapp.Common.Common;
import com.mmuhamadamirzaidi.cutlarapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private Unbinder unbinder;

    @BindView(R.id.subtitle_dashboard_client)
    LinearLayout subtitle_dashboard_client;

    @BindView(R.id.client_informations)
    LinearLayout client_informations;

    @BindView(R.id.txt_username)
    TextView txt_username;

    @BindView(R.id.txt_member_type)
    TextView txt_member_type;

    @BindView(R.id.txt_phone)
    TextView txt_phone;

    @OnClick(R.id.card_view_booking)
    void booking(){
        startActivity(new Intent(getActivity(), BookingActivity.class));
    }

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);

        //Check is signin or not
        if (AccountKit.getCurrentAccessToken() != null){
            setUserInformation();
        }

        return view;
    }

    private void setUserInformation() {
        subtitle_dashboard_client.setVisibility(View.VISIBLE);
        client_informations.setVisibility(View.VISIBLE);

        txt_username.setText(Common.currentUser.getName());
        txt_phone.setText(Common.currentUser.getPhone());
    }

}
