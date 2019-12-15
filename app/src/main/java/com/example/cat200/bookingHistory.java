package com.example.cat200;

public class bookingHistory {
    private String carPlate, date, startTime, endTime, slot;
    private boolean flag;
    private float charge;

    public bookingHistory() {
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public float getCharge() {
        return charge;
    }

    public void setCharge(float charge) {
        this.charge = charge;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public String toString(String returnPlate){
        if (returnPlate.equals(carPlate))
            return "(" + date + ")  " + "Start:" + startTime + "  End:" + endTime + "";
        else
            return "No data";
    }
}
