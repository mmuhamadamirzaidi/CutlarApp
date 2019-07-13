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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mmuhamadamirzaidi.cutlarapp.Common.Common;
import com.mmuhamadamirzaidi.cutlarapp.Model.BookingInformation;
import com.mmuhamadamirzaidi.cutlarapp.R;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class BookingStep4Fragment extends Fragment {

    SimpleDateFormat simpleDateFormat;
    LocalBroadcastManager localBroadcastManager;
    Unbinder unbinder;

    @BindView(R.id.txt_booking_date_text)
    TextView txt_booking_date_text;

    @BindView(R.id.txt_booking_time_text)
    TextView txt_booking_time_text;

    @BindView(R.id.txt_booking_doctor_text)
    TextView txt_booking_doctor_text;

    @BindView(R.id.txt_clinic_address)
    TextView txt_clinic_address;

    @BindView(R.id.txt_clinic_phone)
    TextView txt_clinic_phone;

    @OnClick(R.id.btn_confirm)
    void confirmBooking() {
        BookingInformation bookingInformation = new BookingInformation();

        bookingInformation.setDoctorId(Common.currentDoctor.getDoctorId());
        bookingInformation.setDoctorName(Common.currentDoctor.getName());

        bookingInformation.setClientName(Common.currentUser.getName());

        bookingInformation.setClinicId(Common.currentClinic.getClinicId());
        bookingInformation.setClinicAddress(Common.currentClinic.getAddress());
        bookingInformation.setClinicPhone(Common.currentClinic.getPhone());
        bookingInformation.setClinicName(Common.currentClinic.getName());

        bookingInformation.setTime(new StringBuilder(Common.convertTimeSlotToString(Common.currentTimeSlot)).toString());
        bookingInformation.setDate(simpleDateFormat.format(Common.currentDate.getTime()));
        bookingInformation.setSlot(Long.valueOf(Common.currentTimeSlot));

        //Submit to doctor document
        DocumentReference bookingDate = FirebaseFirestore.getInstance()
                .collection("Clinic")
                .document(Common.city)
                .collection("Branch")
                .document(Common.currentClinic.getClinicId())
                .collection("Doctor")
                .document(Common.currentDoctor.getDoctorId())
                .collection(Common.simpleDateFormat.format(Common.currentDate.getTime()))
                .document(String.valueOf(Common.currentTimeSlot));

        //Write data
        bookingDate.set(bookingInformation)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        getActivity().finish();
                        Toast.makeText(getContext(), "Booking success!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    BroadcastReceiver confirmBookingReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            setData();
        }
    };

    private void setData() {

        txt_booking_time_text.setText(new StringBuilder(Common.convertTimeSlotToString(Common.currentTimeSlot)));
        txt_booking_date_text.setText(simpleDateFormat.format(Common.currentDate.getTime()));

        txt_booking_doctor_text.setText(Common.currentDoctor.getName());

        txt_clinic_address.setText(Common.currentClinic.getAddress());
        txt_clinic_phone.setText(Common.currentClinic.getPhone());
    }

    static BookingStep4Fragment instance;

    public static BookingStep4Fragment getInstance() {
        if (instance == null)
            instance = new BookingStep4Fragment();
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        localBroadcastManager.registerReceiver(confirmBookingReceiver, new IntentFilter(Common.KEY_CONFIRM_BOOKING));
    }

    @Override
    public void onDestroy() {
        localBroadcastManager.unregisterReceiver(confirmBookingReceiver);
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View itemView = inflater.inflate(R.layout.fragment_booking_step_four, container, false);

        unbinder = ButterKnife.bind(this, itemView);

        return itemView;
    }
}
