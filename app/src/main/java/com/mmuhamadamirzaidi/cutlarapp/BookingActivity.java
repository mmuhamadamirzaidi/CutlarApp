package com.mmuhamadamirzaidi.cutlarapp;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mmuhamadamirzaidi.cutlarapp.Adapter.MyViewPagerAdapter;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookingActivity extends AppCompatActivity {

    @BindView(R.id.step_view)
    StepView stepView;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.btn_previous_step)
    Button btn_previous_step;
    @BindView(R.id.btn_next_step)
    Button btn_next_step;
    @BindView(R.id.booking_step)
    TextView booking_step;
    @BindView(R.id.booking_instruction)
    TextView booking_instruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ButterKnife.bind(BookingActivity.this);

        setupStepView();
        setColorButton();

        //View
        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i==0){
                    booking_step.setText("Step 1");
                    booking_instruction.setText("Choose Clinic");
                    btn_previous_step.setEnabled(false);
                    btn_next_step.setText("NEXT");
                }
                else if (i==1){
                    booking_step.setText("Step 2");
                    booking_instruction.setText("Choose Doctor");
                    btn_previous_step.setEnabled(true);
                    btn_next_step.setText("NEXT");
                }
                else if (i==2){
                    booking_step.setText("Step 3");
                    booking_instruction.setText("Choose Time");
                    btn_previous_step.setEnabled(true);
                    btn_next_step.setText("NEXT");
                }
                else if (i==3){
                    booking_step.setText("Step 4");
                    booking_instruction.setText("Confirm Booking");
                    btn_previous_step.setEnabled(true);
                    btn_next_step.setText("CONFIRM");
                }
                setColorButton();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void setColorButton() {
        if (btn_next_step.isEnabled()){
            btn_next_step.setBackgroundResource(R.drawable.bgbtncreate);
        }
        else{
            btn_next_step.setBackgroundResource(R.drawable.bgbtndisable);
        }
        if (btn_previous_step.isEnabled()){
            btn_previous_step.setBackgroundResource(R.drawable.bgbtncreate);
        }
        else{
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
