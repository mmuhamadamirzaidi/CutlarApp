package com.mmuhamadamirzaidi.cutlarapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mmuhamadamirzaidi.cutlarapp.Common.Common;
import com.mmuhamadamirzaidi.cutlarapp.Interface.IRecyclerItemSelectedListener;
import com.mmuhamadamirzaidi.cutlarapp.Model.Doctor;
import com.mmuhamadamirzaidi.cutlarapp.R;

import java.util.ArrayList;
import java.util.List;

public class MyDoctorAdapter extends RecyclerView.Adapter<MyDoctorAdapter.MyViewHolder> {

    Context context;
    List<Doctor> doctorList;

    List<RelativeLayout> relativeLayoutList;
    LocalBroadcastManager localBroadcastManager;

    public MyDoctorAdapter(Context context, List<Doctor> doctorList) {
        this.context = context;
        this.doctorList = doctorList;

        relativeLayoutList = new ArrayList<>();
        localBroadcastManager = LocalBroadcastManager.getInstance(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_doctor, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {

        myViewHolder.img_user.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation));
        myViewHolder.container.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale_animation));

        myViewHolder.txt_doctor_name.setText(doctorList.get(i).getName());
        myViewHolder.ratingBar.setRating((float)doctorList.get(i).getRating());

        if (!relativeLayoutList.contains(myViewHolder.container))
            relativeLayoutList.add(myViewHolder.container);

        myViewHolder.setiRecyclerItemSelectedListener(new IRecyclerItemSelectedListener() {
            @Override
            public void onItemSelectedListener(View view, int pos) {
                //Set white background for container that not selected
                for (RelativeLayout relativeLayout:relativeLayoutList)
                    relativeLayout.setBackground(context.getResources().getDrawable(R.drawable.bg_list));

                //Set color for selected container
//                myViewHolder.container.setBackgroundColor(context.getResources().getColor(R.color.onclick));
                myViewHolder.container.setBackground(context.getResources().getDrawable(R.drawable.bg_onclick));

                //Send Broadcast to tell Booking Activity to enable next button
                Intent intent = new Intent(Common.KEY_ENABLE_BUTTON_NEXT);
                intent.putExtra(Common.KEY_DOCTOR_SELECTED, doctorList.get(pos));
                intent.putExtra(Common.KEY_STEP, 2);
                localBroadcastManager.sendBroadcast(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img_user;
        RelativeLayout container;
        TextView txt_doctor_name;
        RatingBar ratingBar;

        IRecyclerItemSelectedListener iRecyclerItemSelectedListener;

        public void setiRecyclerItemSelectedListener(IRecyclerItemSelectedListener iRecyclerItemSelectedListener) {
            this.iRecyclerItemSelectedListener = iRecyclerItemSelectedListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_doctor_name = (TextView) itemView.findViewById(R.id.txt_doctor_name);
            ratingBar = (RatingBar) itemView.findViewById(R.id.rtd_doctor);

            img_user = itemView.findViewById(R.id.img_user);
            container = itemView.findViewById(R.id.container);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iRecyclerItemSelectedListener.onItemSelectedListener(v, getAdapterPosition());
        }
    }
}
