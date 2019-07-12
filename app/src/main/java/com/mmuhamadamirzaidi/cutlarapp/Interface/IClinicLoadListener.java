package com.mmuhamadamirzaidi.cutlarapp.Interface;

import java.util.List;

public interface IAllClinicLoadListener {

    void onAllClinicLoadSuccess(List<String> areaNameList);
    void onAllClinicLoadFailed(String message);
}
