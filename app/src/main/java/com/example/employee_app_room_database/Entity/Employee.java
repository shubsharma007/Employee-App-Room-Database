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
    @ColumnInfo(name = "age")
    private int age;
    @ColumnInfo(name = "phone")
    private String phone;
    @ColumnInfo(name = "weight")
    private float weight;

    public Employee(int id, String name, int age, String phone, float weight) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.weight = weight;
    }

    @Ignore
    public Employee(String name, int age, String phone, float weight) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.weight = weight;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
