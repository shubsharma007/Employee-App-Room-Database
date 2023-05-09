package com.example.employee_app_room_database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

    String[] genders = {"all employees", "male", "female"};
    ArrayAdapter<String> ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genders);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.selectGender.setAdapter(ad);

        employeeList = new ArrayList<>();
        databaseHelper = DatabaseHelper.getInstance(MainActivity.this);
        showEmployees(0);

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

        binding.deleteAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.employeeDao().deleteAllEmployees();
                showEmployees(binding.selectGender.getSelectedItemPosition());
            }
        });


        binding.selectGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Toast.makeText(MainActivity.this, "All Employees", Toast.LENGTH_SHORT).show();
                    showEmployees(0);
                } else if (position == 1) {
                    showEmployees(1);
                    Toast.makeText(MainActivity.this, "Female", Toast.LENGTH_SHORT).show();
                } else {
                    showEmployees(2);
                    Toast.makeText(MainActivity.this, "Male", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    public void showEmployees(int x) {
        if (x == 0) {
            if (databaseHelper.employeeDao().getAllEmployees().size() > 0) {
                employeeList = databaseHelper.employeeDao().getAllEmployees();
                binding.recyclerViewEmployees.setAdapter(new EmployeeAdapter(employeeList, MainActivity.this, databaseHelper));
                binding.recyclerViewEmployees.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                binding.nothingFoundCard.setVisibility(View.GONE);
                binding.deleteAllBtn.setVisibility(View.VISIBLE);
                binding.recyclerViewEmployees.setVisibility(View.VISIBLE);
            } else {
                binding.deleteAllBtn.setVisibility(View.GONE);
                binding.nothingFoundCard.setVisibility(View.VISIBLE);
                binding.recyclerViewEmployees.setVisibility(View.GONE);
            }
        } else if (x == 1) {
            if (databaseHelper.employeeDao().getAllMales().size() > 0) {
                employeeList = databaseHelper.employeeDao().getAllEmployees();
                binding.recyclerViewEmployees.setAdapter(new EmployeeAdapter(employeeList, MainActivity.this, databaseHelper));
                binding.recyclerViewEmployees.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                binding.nothingFoundCard.setVisibility(View.GONE);
                binding.deleteAllBtn.setVisibility(View.VISIBLE);
                binding.recyclerViewEmployees.setVisibility(View.VISIBLE);
            } else {
                binding.deleteAllBtn.setVisibility(View.GONE);
                binding.nothingFoundCard.setVisibility(View.VISIBLE);
                binding.recyclerViewEmployees.setVisibility(View.GONE);
            }
        } else if (x == 2) {
            if (databaseHelper.employeeDao().getAllFemales().size() > 0) {
                employeeList = databaseHelper.employeeDao().getAllEmployees();
                binding.recyclerViewEmployees.setAdapter(new EmployeeAdapter(employeeList, MainActivity.this, databaseHelper));
                binding.recyclerViewEmployees.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                binding.nothingFoundCard.setVisibility(View.GONE);
                binding.deleteAllBtn.setVisibility(View.VISIBLE);
                binding.recyclerViewEmployees.setVisibility(View.VISIBLE);
            } else {
                binding.deleteAllBtn.setVisibility(View.GONE);
                binding.nothingFoundCard.setVisibility(View.VISIBLE);
                binding.recyclerViewEmployees.setVisibility(View.GONE);
            }
        }

    }
}