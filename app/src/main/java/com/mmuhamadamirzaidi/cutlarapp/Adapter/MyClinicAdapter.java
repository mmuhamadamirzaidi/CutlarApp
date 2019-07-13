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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mmuhamadamirzaidi.cutlarapp.Common.Common;
import com.mmuhamadamirzaidi.cutlarapp.Interface.IRecyclerItemSelectedListener;
import com.mmuhamadamirzaidi.cutlarapp.Model.Clinic;
import com.mmuhamadamirzaidi.cutlarapp.R;

import java.util.ArrayList;
import java.util.List;

public class MyClinicAdapter extends RecyclerView.Adapter<MyClinicAdapter.MyViewHolder> {

    Context context;
    List<Clinic> clinicList;
    List<RelativeLayout> relativeLayoutList;
    LocalBroadcastManager localBroadcastManager;

    public MyClinicAdapter(Context context, List<Clinic> clinicList) {
        this.context = context;
        this.clinicList = clinicList;

        relativeLayoutList = new ArrayList<>();
        localBroadcastManager = LocalBroadcastManager.getInstance(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_clinic, viewGroup, false));

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {

        myViewHolder.img_user.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation));
        myViewHolder.container.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale_animation));

        myViewHolder.txt_clinic_name.setText(clinicList.get(i).getName());
        myViewHolder.txt_clinic_address.setText(clinicList.get(i).getAddress());

        if (!relativeLayoutList.contains(myViewHolder.container))
            relativeLayoutList.add(myViewHolder.container);

        myViewHolder.setiRecyclerItemSelectedListener(new IRecyclerItemSelectedListener() {
            @Override
            public void onItemSelectedListener(View view, int pos) {
                //Set white background for container that not selected
                for (RelativeLayout relativeLayout:relativeLayoutList)
                    relativeLayout.setBackground(context.getResources().getDrawable(R.drawable.bg_list));

                //Set color for selected container
                myViewHolder.container.setBackground(context.getResources().getDrawable(R.drawable.bg_onclick));

                //Send Broadcast to tell Booking Activity to enable next button
                Intent intent = new Intent(Common.KEY_ENABLE_BUTTON_NEXT);
                intent.putExtra(Common.KEY_CLINIC_STORE, clinicList.get(pos));
                intent.putExtra(Common.KEY_STEP, 1);
                localBroadcastManager.sendBroadcast(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return clinicList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img_user;
        RelativeLayout container;
        TextView txt_clinic_name, txt_clinic_address;

        IRecyclerItemSelectedListener iRecyclerItemSelectedListener;

        public void setiRecyclerItemSelectedListener(IRecyclerItemSelectedListener iRecyclerItemSelectedListener) {
            this.iRecyclerItemSelectedListener = iRecyclerItemSelectedListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_clinic_address = (TextView) itemView.findViewById(R.id.txt_clinic_address);
            txt_clinic_name = (TextView) itemView.findViewById(R.id.txt_clinic_name);

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
