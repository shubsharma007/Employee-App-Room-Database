package com.example.employee_app_room_database.BottomSheet;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.employee_app_room_database.Database.DatabaseHelper;
import com.example.employee_app_room_database.Entity.Employee;
import com.example.employee_app_room_database.MainActivity;
import com.example.employee_app_room_database.R;
import com.example.employee_app_room_database.databinding.FragmentAddEmployeeBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddEmployee extends BottomSheetDialogFragment {

    FragmentAddEmployeeBinding binding;

    Context context;
    DatabaseHelper databaseHelper;
    String gender;
    String name;
    String fatherName;
    String dob;
    String phone;
    String email;
    String address;
    String employeeId;
    String designation;
    String experience;
    String salary;

    public AddEmployee(DatabaseHelper databaseHelper, Context context) {
        this.context = context;
        this.databaseHelper = databaseHelper;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddEmployeeBinding.inflate(inflater, container, false);
        View view = inflater.inflate(R.layout.fragment_add_employee, container, false);

        if (getArguments() != null) {
            binding.saveBtn.setText("save changes");
            binding.nameEt.setText(getArguments().getString("Name"));
            binding.fatherNameEt.setText(getArguments().getString("FatherName"));
            binding.dobEt.setText(getArguments().getString("Dob"));
            binding.phoneEt.setText(getArguments().getString("Phone"));
            binding.emailEt.setText(getArguments().getString("Email"));
            binding.addressEt.setText(getArguments().getString("Address"));
            binding.employeeId.setText(getArguments().getString("EmployeeId"));
            binding.designitionEt.setText(getArguments().getString("Designation"));
            binding.experienceEt.setText(getArguments().getString("Experience"));
            binding.salaryEt.setText(String.valueOf(getArguments().getFloat("Salary")));

            if (getArguments().getString("Gender").equals("male")) {
                binding.rbMale.setChecked(true);
            } else {
                binding.rbFemale.setChecked(true);
            }

            if (getArguments().getBoolean("MaritalStatus")) {
                binding.rbMarried.setChecked(true);
            } else {
                binding.rbUnmarried.setChecked(true);
            }
        }

        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int selectedId = binding.rgGender.getCheckedRadioButtonId();
                RadioButton genderRb = (RadioButton) view.findViewById(selectedId);

                selectedId = binding.rgMaritalStatus.getCheckedRadioButtonId();
                RadioButton maritalStatus = (RadioButton) view.findViewById(selectedId);
                boolean marriage = false;


                if (getArguments() != null) {
                    int Id = getArguments().getInt("Id");


                    name = binding.nameEt.getText().toString();
                    fatherName = binding.fatherNameEt.getText().toString();
                    dob = binding.dobEt.getText().toString();
                    phone = binding.phoneEt.getText().toString();
                    email = binding.emailEt.getText().toString();
                    address = binding.addressEt.getText().toString();
                    employeeId = binding.employeeId.getText().toString();
                    designation = binding.designitionEt.getText().toString();
                    experience = binding.experienceEt.getText().toString();
                    salary = binding.salaryEt.getText().toString();
                    gender = genderRb.getText().toString();

                    if (maritalStatus.getText().toString().equals("married")) {
                        marriage = true;
                    }
                    addNewEmployeeFunc(Id, name, fatherName, dob, gender, phone, email, address, employeeId, designation, experience, marriage, Float.parseFloat(salary));

                } else {
                    name = binding.nameEt.getText().toString();
                    fatherName = binding.fatherNameEt.getText().toString();
                    dob = binding.dobEt.getText().toString();
                    phone = binding.phoneEt.getText().toString();
                    email = binding.emailEt.getText().toString();
                    address = binding.addressEt.getText().toString();
                    employeeId = binding.employeeId.getText().toString();
                    designation = binding.designitionEt.getText().toString();
                    experience = binding.experienceEt.getText().toString();
                    salary = binding.salaryEt.getText().toString();
                    gender = genderRb.getText().toString();

                    if (maritalStatus.getText().toString().equals("married")) {
                        marriage = true;
                    }
                    addNewEmployeeFunc(0, name, fatherName, dob, gender, phone, email, address, employeeId, designation, experience, marriage, Float.parseFloat(salary));
                }

            }
        });


        return (binding.getRoot());
    }


    public void addNewEmployeeFunc(int Id, String name, String fatherName, String dob, String gender, String phone, String email, String address, String employeeId, String designation, String experience, boolean maritalStatus, float salary) {
        if (Id == 0) {
            databaseHelper.employeeDao().addEmployee(new Employee(name, fatherName, dob, gender, phone, email, address, employeeId, designation, experience, maritalStatus, salary));
            Toast.makeText(context, "employee added successfully", Toast.LENGTH_SHORT).show();
        } else {
            databaseHelper.employeeDao().updateEmployee(new Employee(Id, name, fatherName, dob, gender, phone, email, address, employeeId, designation, experience, maritalStatus, salary));
            Toast.makeText(context, "employee updated successfully", Toast.LENGTH_SHORT).show();
        }
        this.dismiss();

        ((MainActivity) context).showEmployees(0);
    }
}