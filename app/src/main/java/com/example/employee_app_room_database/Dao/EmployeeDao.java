package com.example.employee_app_room_database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.employee_app_room_database.Entity.Employee;

import java.util.List;

@Dao
public interface EmployeeDao {
    @Insert
    void addEmployee(Employee employee);

    @Update
    void updateEmployee(Employee employee);

    @Delete
    void deleteEmployee(Employee employee);

    @Query("SELECT * FROM Employee")
    List<Employee> getAllEmployees();
}
