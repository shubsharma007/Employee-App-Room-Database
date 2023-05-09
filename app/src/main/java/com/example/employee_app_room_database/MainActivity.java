package com.example.employee_app_room_database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.employee_app_room_database.Adapter.EmployeeAdapter;
import com.example.employee_app_room_database.BottomSheet.AddEmployee;
import com.example.employee_app_room_database.Database.DatabaseHelper;
import com.example.employee_app_room_database.Entity.Employee;
import com.example.employee_app_room_database.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    List<Employee> employeeList;
    DatabaseHelper databaseHelper;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        employeeList = new ArrayList<>();
        databaseHelper = DatabaseHelper.getInstance(MainActivity.this);
        showEmployees();

        binding.addFirstEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddEmployee addEmp = new AddEmployee(databaseHelper, MainActivity.this);
                addEmp.show(getSupportFragmentManager(), addEmp.getTag());
            }
        });

        binding.addEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.addFirstEmployee.performClick();
            }
        });
    }



    public void showEmployees() {
        if (databaseHelper.employeeDao().getAllEmployees().size() > 0) {
            employeeList = databaseHelper.employeeDao().getAllEmployees();
            binding.recyclerViewEmployees.setAdapter(new EmployeeAdapter(employeeList, MainActivity.this, databaseHelper));
            binding.recyclerViewEmployees.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            binding.nothingFoundCard.setVisibility(View.GONE);
            binding.recyclerViewEmployees.setVisibility(View.VISIBLE);
        } else {
            binding.nothingFoundCard.setVisibility(View.VISIBLE);
            binding.recyclerViewEmployees.setVisibility(View.GONE);
        }
    }
}