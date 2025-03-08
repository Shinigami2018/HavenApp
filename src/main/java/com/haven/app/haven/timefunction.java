package com.haven.app.haven;

import java.time.*;

public class timefunction {
    public int uhour;
    public int uminute;
    public int usecond;
    public int uday;
    public int umonth;
    public int uyear;

    public timefunction(int hour, int minute, int second, int day, int month, int year) {
        this.uhour = hour;
        this.uminute = minute;
        this.usecond = second;
        this.uday = day;
        this.umonth = month;
        this.uyear = year;
    }
//    public timefunction(int hour, int minute, int second) {
//        this.uhour = hour;
//        this.uminute = minute;
//        this.usecond = second;
//        this.uday = 0;
//        this.umonth = 0;
//        this.uyear = 0;
//    }
//    public timefunction(int day, int month) {
//        this.uhour = 0;
//        this.uminute = 0;
//        this.usecond = 0;
//        this.uday = day;
//        this.umonth = month;
//        this.uyear = 0;
//    }

    public timefunction() {
        LocalDateTime now = LocalDateTime.now();
        this.uhour = now.getHour();
        this.uminute = now.getMinute();
        this.usecond = now.getSecond();
        this.uday = now.getDayOfMonth();
        this.umonth = now.getMonthValue();
        this.uyear = now.getYear();
    }
    public boolean checkTime(int hour, int minute, int second, int day, int month, int year) {
        return uhour == hour && uminute == minute && usecond == second && uday == day && umonth == month && uyear == year;
    }

    @Override
    public String toString() {
        return String.format("%02d-%02d-%04d %02d:%02d:%02d", uday, umonth, uyear, uhour, uminute, usecond);
    }

}

