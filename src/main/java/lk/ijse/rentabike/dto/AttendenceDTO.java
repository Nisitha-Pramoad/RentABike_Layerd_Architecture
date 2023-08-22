package lk.ijse.rentabike.dto;

import java.time.LocalDate;

public class AttendenceDTO {
    private String attendenceId;
    private String date;
    private String holiday;
    private String signInTime;
    private String signOutTime;
    private String employeeId;

    public AttendenceDTO() {
    }

    public AttendenceDTO(String attendenceId, String date, String holiday, String signInTime, String signOutTime, String employeeId) {
        this.attendenceId = attendenceId;
        this.date = date;
        this.holiday = holiday;
        this.signInTime = signInTime;
        this.signOutTime = signOutTime;
        this.employeeId = employeeId;
    }

    public AttendenceDTO(String attendenceId, LocalDate date, String holiday, String signInTime, String signOutTime, String employeedId) {
        this.attendenceId=attendenceId;
        this.date= String.valueOf(date);
        this.holiday=holiday;
        this.signInTime=signInTime;
        this.signOutTime=signOutTime;
        this.employeeId=employeedId;
    }

    public String getAttendenceId() {
        return attendenceId;
    }

    public void setAttendenceId(String attendenceId) {
        this.attendenceId = attendenceId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public String getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(String signInTime) {
        this.signInTime = signInTime;
    }

    public String getSignOutTime() {
        return signOutTime;
    }

    public void setSignOutTime(String signOutTime) {
        this.signOutTime = signOutTime;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "AttendenceDTO{" +
                "attendenceId='" + attendenceId + '\'' +
                ", date='" + date + '\'' +
                ", holiday='" + holiday + '\'' +
                ", signInTime='" + signInTime + '\'' +
                ", signOutTime='" + signOutTime + '\'' +
                ", employeeId='" + employeeId + '\'' +
                '}';
    }
}
