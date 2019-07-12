package com.mmuhamadamirzaidi.cutlarapp.Interface;

import com.mmuhamadamirzaidi.cutlarapp.Model.Clinic;

import java.util.List;

public interface IBranchLoadListener {

    void onBranchLoadSuccess(List<Clinic> clinicList);
    void onBranchLoadFailed(String message);
}
