package com.mmuhamadamirzaidi.cutlarapp.Model;

public class BookingInformation {

    private String clientName, clientPhone, date, doctorId, doctorName, clinicId, clinicName, clinicAddress;
    private Long slot;

    public BookingInformation() {
    }

    public BookingInformation(String clientName, String clientPhone, String date, String doctorId, String doctorName, String clinicId, String clinicName, String clinicAddress, Long slot) {
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.date = date;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.clinicId = clinicId;
        this.clinicName = clinicName;
        this.clinicAddress = clinicAddress;
        this.slot = slot;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getClinicAddress() {
        return clinicAddress;
    }

    public void setClinicAddress(String clinicAddress) {
        this.clinicAddress = clinicAddress;
    }

    public Long getSlot() {
        return slot;
    }

    public void setSlot(Long slot) {
        this.slot = slot;
    }
}
