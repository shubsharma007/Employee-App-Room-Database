package com.example.employee_app_room_database.Entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Employee")
public class Employee {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "fatherName")
    private String fatherName;
    @ColumnInfo(name = "dob")
    private String dob;
    @ColumnInfo(name = "gender")
    private String gender;
    @ColumnInfo(name = "phone")
    private String phone;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "address")
    private String address;
    @ColumnInfo(name = "employeeId")
    private String employeeId;
    @ColumnInfo(name = "designation")
    private String designation;
    @ColumnInfo(name = "experience")
    private String experience;
    @ColumnInfo(name = "maritalStatus")
    private boolean maritalStatus;
    @ColumnInfo(name = "salary")
    private float salary;


    public Employee(int id, String name, String fatherName, String dob, String gender, String phone, String email, String address, String employeeId, String designation, String experience, boolean maritalStatus, float salary) {
        this.id = id;
        this.name = name;
        this.fatherName = fatherName;
        this.dob = dob;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.employeeId = employeeId;
        this.designation = designation;
        this.experience = experience;
        this.maritalStatus = maritalStatus;
        this.salary = salary;
    }

    @Ignore
    public Employee(String name, String fatherName, String dob, String gender, String phone, String email, String address, String employeeId, String designation, String experience, boolean maritalStatus, float salary) {
        this.name = name;
        this.fatherName = fatherName;
        this.dob = dob;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.employeeId = employeeId;
        this.designation = designation;
        this.experience = experience;
        this.maritalStatus = maritalStatus;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public boolean isMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(boolean maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
}