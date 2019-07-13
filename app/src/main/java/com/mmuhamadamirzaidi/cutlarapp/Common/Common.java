package com.mmuhamadamirzaidi.cutlarapp.Common;

import com.mmuhamadamirzaidi.cutlarapp.Model.Clinic;
import com.mmuhamadamirzaidi.cutlarapp.Model.User;

public class Common {
    public static final String KEY_ENABLE_BUTTON_NEXT = "ENABLE_BUTTON_NEXT";
    public static final String KEY_CLINIC_STORE = "CLINIC_SAVE";
    public static final String KEY_DOCTOR_LOAD_DONE = "BARBER_LOAD_DONE";
    public static String IS_SIGNIN = "IsSignIn";
    public static User currentUser;
    public static Clinic currentClinic;
    public static int step = 0; //Init first step is 0
    public static String city = "";
}
