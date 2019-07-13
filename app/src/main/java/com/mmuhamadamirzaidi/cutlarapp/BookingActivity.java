package com.mmuhamadamirzaidi.cutlarapp;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mmuhamadamirzaidi.cutlarapp.Adapter.MyViewPagerAdapter;
import com.mmuhamadamirzaidi.cutlarapp.Common.Common;
import com.mmuhamadamirzaidi.cutlarapp.Common.NonSwipeViewPager;
import com.mmuhamadamirzaidi.cutlarapp.Model.Doctor;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;

public class BookingActivity extends AppCompatActivity {

    LocalBroadcastManager localBroadcastManager;
    AlertDialog dialog;
    CollectionReference doctorRef;

    @BindView(R.id.step_view)
    StepView stepView;
    @BindView(R.id.view_pager)
    NonSwipeViewPager viewPager;
    @BindView(R.id.btn_previous_step)
    Button btn_previous_step;
    @BindView(R.id.btn_next_step)
    Button btn_next_step;
    @BindView(R.id.booking_step)
    TextView booking_step;
    @BindView(R.id.booking_instruction)
    TextView booking_instruction;

    //Event
    @OnClick(R.id.btn_previous_step)
    void previousClick() {
        if (Common.step == 3 || Common.step > 0) {
            Common.step--;
            viewPager.setCurrentItem(Common.step);
        }
    }

    @OnClick(R.id.btn_next_step)
    void nextClick() {
        Toast.makeText(this, "" + Common.currentClinic.getClinicId(), Toast.LENGTH_SHORT).show();

        if (Common.step < 3 || Common.step == 0) {
            Common.step++; //Increase
            if (Common.step == 1) { //After choose clinic
                if (Common.currentClinic != null) {
                    loadDoctorByClinic(Common.currentClinic.getClinicId());
                }
            }
            else if(Common.step == 2){ //Pick time slot
                if (Common.currentDoctor != null)
                    loadTimeSlotDoctor(Common.currentDoctor.getDoctorId());
            }
            else if(Common.step == 3){ //Confirm booking
                if (Common.currentTimeSlot != -1)
                    confirmBooking();
            }
            viewPager.setCurrentItem(Common.step);
        }
    }

    private void confirmBooking() {
        //Send Local Broadcast to Fragment step 4
        Intent intent = new Intent(Common.KEY_CONFIRM_BOOKING);
        localBroadcastManager.sendBroadcast(intent);
    }

    private void loadTimeSlotDoctor(String doctorId) {
        //Send Local Broadcast to Fragment step 3
        Intent intent = new Intent(Common.KEY_DISPLAY_TIME_SLOT);
        localBroadcastManager.sendBroadcast(intent);
    }

    private void loadDoctorByClinic(String clinicId) {
        dialog.show();

        if (!TextUtils.isEmpty(Common.city)) {
            doctorRef = FirebaseFirestore.getInstance()
                    .collection("Clinic")
                    .document(Common.city)
                    .collection("Branch")
                    .document(clinicId)
                    .collection("Doctor");

            doctorRef.get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            ArrayList<Doctor> doctors = new ArrayList<>();

                            for (QueryDocumentSnapshot doctorSnapShot : task.getResult()) {
                                Doctor doctor = doctorSnapShot.toObject(Doctor.class);
                                doctor.setPassword(""); //Remove password (client app)
                                doctor.setDoctorId(doctorSnapShot.getId()); //Get id of doctor

                                doctors.add(doctor);
                            }

                            //Send broadcast to BookingStep2Fragment to load recycler view
                            Intent intent = new Intent(Common.KEY_DOCTOR_LOAD_DONE);
                            intent.putParcelableArrayListExtra(Common.KEY_DOCTOR_LOAD_DONE, doctors);
                            localBroadcastManager.sendBroadcast(intent);

                            dialog.dismiss();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            dialog.dismiss();
                        }
                    });
        }
    }

    //Broadcast Receiver
    private BroadcastReceiver buttonNextReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int step = intent.getIntExtra(Common.KEY_STEP, 0);

            if (step == 1)
                Common.currentClinic = intent.getParcelableExtra(Common.KEY_CLINIC_STORE);
            else if (step == 2)
                Common.currentDoctor = intent.getParcelableExtra(Common.KEY_DOCTOR_SELECTED);
            else if (step == 3)
                Common.currentTimeSlot = intent.getIntExtra(Common.KEY_TIME_SLOT, -1);

            btn_next_step.setEnabled(true);
            setColorButton();
        }
    };

    @Override
    protected void onDestroy() {
        localBroadcastManager.unregisterReceiver(buttonNextReceiver);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ButterKnife.bind(BookingActivity.this);

        dialog = new SpotsDialog.Builder().setContext(this).build();

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.registerReceiver(buttonNextReceiver, new IntentFilter(Common.KEY_ENABLE_BUTTON_NEXT));

        setupStepView();
        setColorButton();

        //View
        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(4); //4 fragment, so, need to keep state of all fragment until process finish
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                stepView.go(i, true);

                if (i == 0) {
                    booking_step.setText("Step 1");
                    booking_instruction.setText("Choose Clinic");
                    btn_previous_step.setEnabled(false);
                    btn_next_step.setText("NEXT");
                } else if (i == 1) {
                    booking_step.setText("Step 2");
                    booking_instruction.setText("Choose Doctor");
                    btn_previous_step.setEnabled(true);
                    btn_next_step.setText("NEXT");
                } else if (i == 2) {
                    booking_step.setText("Step 3");
                    booking_instruction.setText("Choose Time");
                    btn_previous_step.setEnabled(true);
                    btn_next_step.setText("NEXT");
                } else if (i == 3) {
                    booking_step.setText("Step 4");
                    booking_instruction.setText("Confirm Booking");
                    btn_previous_step.setEnabled(true);
//                    btn_next_step.setEnabled(false);
                    btn_next_step.setText("NEXT");
                }
                btn_next_step.setEnabled(false);
                setColorButton();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void setColorButton() {
        if (btn_next_step.isEnabled()) {
            btn_next_step.setBackgroundResource(R.drawable.bgbtncreate);
        } else {
            btn_next_step.setBackgroundResource(R.drawable.bgbtndisable);
        }
        if (btn_previous_step.isEnabled()) {
            btn_previous_step.setBackgroundResource(R.drawable.bgbtncreate);
        } else {
            btn_previous_step.setBackgroundResource(R.drawable.bgbtndisable);
        }
    }

    private void setupStepView() {
        List<String> stepList = new ArrayList<>();
        stepList.add("Clinic");
        stepList.add("Doctor");
        stepList.add("Time");
        stepList.add("Confirm");
        stepView.setSteps(stepList);
    }
}
