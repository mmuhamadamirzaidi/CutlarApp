package com.mmuhamadamirzaidi.cutlarapp.Fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmuhamadamirzaidi.cutlarapp.Adapter.MyDoctorAdapter;
import com.mmuhamadamirzaidi.cutlarapp.Common.Common;
import com.mmuhamadamirzaidi.cutlarapp.Common.SpacesItemDecoration;
import com.mmuhamadamirzaidi.cutlarapp.Model.Doctor;
import com.mmuhamadamirzaidi.cutlarapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BookingStep2Fragment extends Fragment {

    Unbinder unbinder;
    LocalBroadcastManager localBroadcastManager;

    @BindView(R.id.recycler_doctor)
    RecyclerView recycler_doctor;

    private BroadcastReceiver doctorDoneReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ArrayList<Doctor> doctorArrayList = intent.getParcelableArrayListExtra(Common.KEY_DOCTOR_LOAD_DONE);

            MyDoctorAdapter adapter = new MyDoctorAdapter(getContext(), doctorArrayList);
            recycler_doctor.setAdapter(adapter);

        }
    };

    static BookingStep2Fragment instance;

    public static BookingStep2Fragment getInstance() {
        if (instance ==null)
            instance = new BookingStep2Fragment();
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        localBroadcastManager.registerReceiver(doctorDoneReceiver, new IntentFilter(Common.KEY_DOCTOR_LOAD_DONE));
    }

    @Override
    public void onDestroy() {
        localBroadcastManager.unregisterReceiver(doctorDoneReceiver);
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View itemView = inflater.inflate(R.layout.fragment_booking_step_two, container, false);
        unbinder = ButterKnife.bind(this, itemView);

        initView();

        return itemView;
    }

    private void initView() {
        recycler_doctor.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recycler_doctor.setLayoutManager(gridLayoutManager);

        recycler_doctor.addItemDecoration(new SpacesItemDecoration(4));
    }
}
