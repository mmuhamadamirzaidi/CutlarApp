package com.mmuhamadamirzaidi.cutlarapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.mmuhamadamirzaidi.cutlarapp.Model.Clinic;
import com.mmuhamadamirzaidi.cutlarapp.R;

import java.util.List;

public class MyClinicAdapter extends RecyclerView.Adapter<MyClinicAdapter.MyViewHolder> {

    Context context;
    List<Clinic> clinicList;

    public MyClinicAdapter(Context context, List<Clinic> clinicList) {
        this.context = context;
        this.clinicList = clinicList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_clinic, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

//        myViewHolder.img_user.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
//        myViewHolder.container.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));


        myViewHolder.txt_clinic_name.setText(clinicList.get(i).getName());
        myViewHolder.txt_clinic_address.setText(clinicList.get(i).getAddress());
    }

    @Override
    public int getItemCount() {
        return clinicList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txt_clinic_name, txt_clinic_address;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_clinic_name = (TextView)itemView.findViewById(R.id.txt_clinic_name);
            txt_clinic_address = (TextView)itemView.findViewById(R.id.txt_clinic_address);
        }
    }
}
