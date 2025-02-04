package com.mmuhamadamirzaidi.cutlarapp.Interface;

import com.mmuhamadamirzaidi.cutlarapp.Model.TimeSlot;

import java.sql.Time;
import java.util.List;

public interface ITimeSlotLoadListener {

    void onTimeSlotLoadSuccess(List<TimeSlot> timeSlotList);
    void onTimeSlotLoadFailed(String message);
    void onTimeSlotLoadEmpty();
}
