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

    @Query("DELETE FROM Employee")
    void deleteAllEmployees();

    @Query("SELECT * FROM Employee WHERE gender = 'male' ")
    List<Employee> getAllMales();

    @Query("SELECT * FROM Employee WHERE gender = 'female' ")
    List<Employee> getAllFemales();

    // Get a user by ID
    @Query("SELECT * FROM Employee WHERE id = :id")
    Employee getEmployeeById(int id);
}
