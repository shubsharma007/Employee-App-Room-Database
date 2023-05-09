package com.example.employee_app_room_database.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.employee_app_room_database.Dao.EmployeeDao;
import com.example.employee_app_room_database.Entity.Employee;

@Database(entities = {Employee.class}, version = 3, exportSchema = false)
public abstract class DatabaseHelper extends RoomDatabase {
    public static final String DATABASE_NAME = "employeeDatabase";

    public abstract EmployeeDao employeeDao();

    public static DatabaseHelper instance;

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), DatabaseHelper.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
