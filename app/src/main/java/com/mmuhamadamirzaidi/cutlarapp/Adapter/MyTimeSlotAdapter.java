package com.mmuhamadamirzaidi.cutlarapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mmuhamadamirzaidi.cutlarapp.Common.Common;
import com.mmuhamadamirzaidi.cutlarapp.Model.Doctor;
import com.mmuhamadamirzaidi.cutlarapp.Model.TimeSlot;
import com.mmuhamadamirzaidi.cutlarapp.R;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class MyTimeSlotAdapter extends RecyclerView.Adapter<MyTimeSlotAdapter.MyViewHolder> {

    Context context;
    List<TimeSlot> timeSlotList;

    public MyTimeSlotAdapter(Context context) {
        this.context = context;
        this.timeSlotList = new ArrayList<>();
    }

    public MyTimeSlotAdapter(Context context, List<TimeSlot> timeSlotList) {
        this.context = context;
        this.timeSlotList = timeSlotList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_time, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.img_user.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation));
        myViewHolder.container.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale_animation));

        myViewHolder.txt_time_slot.setText(new StringBuilder(Common.convertTimeSlotToString(i)).toString());

        if (timeSlotList.size() == 0){ //If all position is available, show list
            myViewHolder.txt_time_slot_description.setText("Available");
            myViewHolder.txt_time_slot_description.setTextColor(context.getResources().getColor(R.color.done));
            myViewHolder.container.setBackground(context.getResources().getDrawable(R.drawable.bg_list));
        }
        else{ //If already booked
            for (TimeSlot slotValue:timeSlotList){

                //Loop all time slot from server and set different color
                int slot = Integer.parseInt(slotValue.getSlot().toString());
                if (slot == i){ //If slot == position
                    myViewHolder.txt_time_slot_description.setText("Booked");
                    myViewHolder.txt_time_slot.setTextColor(context.getResources().getColor(R.color.white));
                    myViewHolder.txt_time_slot_description.setTextColor(context.getResources().getColor(R.color.white));
                    myViewHolder.container.setBackground(context.getResources().getDrawable(R.drawable.bg_booked));
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return Common.TIME_SLOT_TOTAL;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView img_user;
        RelativeLayout container;
        TextView txt_time_slot, txt_time_slot_description;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_time_slot = (TextView) itemView.findViewById(R.id.txt_time_slot);
            txt_time_slot_description = (TextView) itemView.findViewById(R.id.txt_time_slot_description);

            img_user = itemView.findViewById(R.id.img_user);
            container = itemView.findViewById(R.id.container);
        }
    }
}