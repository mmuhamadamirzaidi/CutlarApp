package com.mmuhamadamirzaidi.cutlarapp.Common;

import com.mmuhamadamirzaidi.cutlarapp.Model.Clinic;
import com.mmuhamadamirzaidi.cutlarapp.Model.Doctor;
import com.mmuhamadamirzaidi.cutlarapp.Model.User;

public class Common {
    public static final String KEY_ENABLE_BUTTON_NEXT = "ENABLE_BUTTON_NEXT";
    public static final String KEY_CLINIC_STORE = "CLINIC_SAVE";
    public static final String KEY_DOCTOR_LOAD_DONE = "BARBER_LOAD_DONE";
    public static final String KEY_DISPLAY_TIME_SLOT = "DISPLAY_TIME_SLOT";
    public static final String KEY_STEP = "STEP";
    public static final String KEY_DOCTOR_SELECTED = "BARBER_SELECTED";
    public static final int TIME_SLOT_TOTAL = 24;
    public static String IS_SIGNIN = "IsSignIn";
    public static User currentUser;
    public static Clinic currentClinic;
    public static int step = 0; //Init first step is 0
    public static String city = "";
    public static Doctor currentDoctor;

    public static String convertTimeSlotToString(int slot) {
        switch (slot){
            case 0:
                return "8:00 AM - 8:20 AM";
            case 1:
                return "8:20 AM - 8:40 AM";
            case 2:
                return "8:40 AM - 9:00 AM"; //Booked
            case 3:
                return "9:00 AM - 9:20 AM";
            case 4:
                return "9:20 AM - 9:40 AM";
            case 5:
                return "9:40 AM - 10:00 AM";
            case 6:
                return "10:00 AM - 10:20 AM";
            case 7:
                return "10:20 AM - 10:40 AM";
            case 8:
                return "10:40 AM - 11:00 AM";
            case 9:
                return "11:00 AM - 11:20 AM"; //Booked init
            case 10:
                return "11:20 AM - 11:40 AM";
            case 11:
                return "11:40 AM - 12:00 NOON";
            case 12:
                return "12:00 NOON - 12:20 PM";
            case 13:
                return "12:20 PM - 12:40 PM";
            case 14:
                return "12:40 PM - 1:00 PM"; //Start 1PM, lunch hour until 2PM
            case 15:
                return "2:00 PM - 2:20 PM"; //Start again at 2PM until close
            case 16:
                return "2:20 PM - 2:40 PM"; //Booked
            case 17:
                return "2:40 PM - 3:00 PM";
            case 18:
                return "3:00 PM - 3:20 PM";
            case 19:
                return "3:20 PM - 3:40 PM";
            case 20:
                return "3:40 PM - 4:00 PM";
            case 21:
                return "4:00 PM - 4:20 PM";
            case 22:
                return "4:20 PM - 4:40 PM";
            case 23:
                return "4:40 PM - 5:00 PM"; //Booked
            default:
                return "Closed";
        }
    }
}
