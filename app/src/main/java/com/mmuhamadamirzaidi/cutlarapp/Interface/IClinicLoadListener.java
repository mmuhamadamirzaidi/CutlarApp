package com.mmuhamadamirzaidi.cutlarapp.Interface;

import java.util.List;

public interface IClinicLoadListener {

    void onClinicLoadSuccess(List<String> areaNameList);
    void onClinicLoadFailed(String message);
}
