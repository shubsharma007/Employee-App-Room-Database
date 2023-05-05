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
                dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_employee_dialog);
                dialog.setCancelable(false);

                EditText et1, et2, et3, et4;
                et1 = dialog.findViewById(R.id.et1);
                et2 = dialog.findViewById(R.id.et2);
                et3 = dialog.findViewById(R.id.et3);
                et4 = dialog.findViewById(R.id.et4);
                Button saveBtn = dialog.findViewById(R.id.saveBtn);
                saveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addNewEmployeeFunc(et1.getText().toString(), et2.getText().toString(), et3.getText().toString(), et4.getText().toString(), 0);
                    }
                });
                dialog.show();
            }
        });

        binding.addEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.addFirstEmployee.performClick();
            }
        });
    }

    public void addNewEmployeeFunc(String name, String age, String phone, String weight, int Id) {
        if (Id == 0) {
            databaseHelper.employeeDao().addEmployee(new Employee(name, Integer.parseInt(age), phone, Float.parseFloat(weight)));
            Toast.makeText(this, "employee added successfully", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        } else {
            databaseHelper.employeeDao().updateEmployee(new Employee(Id, name, Integer.parseInt(age), phone, Float.parseFloat(weight)));
            Toast.makeText(this, "employee updated successfully", Toast.LENGTH_SHORT).show();
        }

        showEmployees();
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