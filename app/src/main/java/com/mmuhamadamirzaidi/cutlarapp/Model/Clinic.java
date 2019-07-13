package com.mmuhamadamirzaidi.cutlarapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Clinic implements Parcelable {

    private String name, address, phone, clinicId;

    public Clinic() {
    }

    protected Clinic(Parcel in) {
        name = in.readString();
        address = in.readString();
        phone = in.readString();
        clinicId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(phone);
        dest.writeString(clinicId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Clinic> CREATOR = new Creator<Clinic>() {
        @Override
        public Clinic createFromParcel(Parcel in) {
            return new Clinic(in);
        }

        @Override
        public Clinic[] newArray(int size) {
            return new Clinic[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }
}
