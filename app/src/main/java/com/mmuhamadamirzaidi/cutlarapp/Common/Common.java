package com.mmuhamadamirzaidi.cutlarapp.Common;

import com.mmuhamadamirzaidi.cutlarapp.Model.Clinic;
import com.mmuhamadamirzaidi.cutlarapp.Model.Doctor;
import com.mmuhamadamirzaidi.cutlarapp.Model.TimeSlot;
import com.mmuhamadamirzaidi.cutlarapp.Model.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Common {
    public static final String KEY_ENABLE_BUTTON_NEXT = "ENABLE_BUTTON_NEXT";
    public static final String KEY_CLINIC_STORE = "CLINIC_SAVE";
    public static final String KEY_DOCTOR_LOAD_DONE = "DOCTOR_LOAD_DONE";
    public static final String KEY_DISPLAY_TIME_SLOT = "DISPLAY_TIME_SLOT";
    public static final String KEY_STEP = "STEP";
    public static final String KEY_DOCTOR_SELECTED = "DOCTOR_SELECTED";
    public static final int TIME_SLOT_TOTAL = 24;
    public static final Object DISABLE_TAG = "DISABLE";
    public static final String KEY_TIME_SLOT = "TIME_SLOT";
    public static final String KEY_CONFIRM_BOOKING = "CONFIRM_BOOKING";
    public static String IS_SIGNIN = "IsSignIn";
    public static User currentUser;
    public static Clinic currentClinic;
    public static int step = 0; //Init first step is 0
    public static String city = "";
    public static Doctor currentDoctor;
    public static int currentTimeSlot = -1;
    public static Calendar currentDate = Calendar.getInstance();
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd_MM_yyyy"); //Use when need date as key

    public static String convertTimeSlotToString(int slot) {
        switch (slot){
            case 0:
                return "8:00 am - 8:20 am";
            case 1:
                return "8:20 am - 8:40 am";
            case 2:
                return "8:40 am - 9:00 am"; //Booked
            case 3:
                return "9:00 am - 9:20 am";
            case 4:
                return "9:20 am - 9:40 am";
            case 5:
                return "9:40 am - 10:00 am";
            case 6:
                return "10:00 am - 10:20 am";
            case 7:
                return "10:20 am - 10:40 am";
            case 8:
                return "10:40 am - 11:00 am";
            case 9:
                return "11:00 am - 11:20 am"; //Booked init
            case 10:
                return "11:20 am - 11:40 am";
            case 11:
                return "11:40 am - 12:00 noon";
            case 12:
                return "12:00 noon - 12:20 pm";
            case 13:
                return "12:20 pm - 12:40 pm";
            case 14:
                return "12:40 pm - 1:00 pm"; //Start 1PM, lunch hour until 2PM
            case 15:
                return "2:00 pm - 2:20 pm"; //Start again at 2PM until close
            case 16:
                return "2:20 pm - 2:40 pm"; //Booked
            case 17:
                return "2:40 pm - 3:00 pm";
            case 18:
                return "3:00 pm - 3:20 pm";
            case 19:
                return "3:20 pm - 3:40 pm";
            case 20:
                return "3:40 pm - 4:00 pm";
            case 21:
                return "4:00 pm - 4:20 pm";
            case 22:
                return "4:20 pm - 4:40 pm";
            case 23:
                return "4:40 pm - 5:00 pm"; //Booked
            default:
                return "Closed";
        }
    }
}
