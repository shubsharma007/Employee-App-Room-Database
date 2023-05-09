package com.example.employee_app_room_database.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employee_app_room_database.BottomSheet.AddEmployee;
import com.example.employee_app_room_database.Database.DatabaseHelper;
import com.example.employee_app_room_database.Entity.Employee;
import com.example.employee_app_room_database.MainActivity;
import com.example.employee_app_room_database.R;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {
    private List<Employee> employeeList;
    Context context;

    DatabaseHelper databaseHelper;

    public EmployeeAdapter(List<Employee> employeeList, Context context, DatabaseHelper databaseHelper) {
        this.employeeList = employeeList;
        this.context = context;
        this.databaseHelper = databaseHelper;
    }

    @NonNull
    @Override
    public EmployeeAdapter.EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EmployeeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_employee, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.EmployeeViewHolder holder, int position) {
        Employee singleUnit = employeeList.get(position);

        holder.name.setText("name- " + singleUnit.getName());

        holder.phone.setText("phone- " + singleUnit.getPhone());

        holder.eId.setText("employee Id- " + String.valueOf(singleUnit.getEmployeeId()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // bottom sheet khol do

                Bundle bundle = new Bundle();
                bundle.putInt("Id", singleUnit.getId());
                bundle.putString("Name", singleUnit.getName());
                bundle.putString("FatherName", singleUnit.getFatherName());
                bundle.putString("Dob", singleUnit.getDob());
                bundle.putString("Gender", singleUnit.getGender());
                bundle.putString("Phone", singleUnit.getPhone());
                bundle.putString("Email", singleUnit.getEmail());
                bundle.putString("Address", singleUnit.getAddress());
                bundle.putString("EmployeeId", singleUnit.getEmployeeId());
                bundle.putString("Designation", singleUnit.getDesignation());
                bundle.putString("Experience", singleUnit.getExperience());
                bundle.putBoolean("MaritalStatus", singleUnit.isMaritalStatus());
                bundle.putFloat("Salary", singleUnit.getSalary());

                AddEmployee addEmp = new AddEmployee(databaseHelper, context);
                FragmentManager fm = ((AppCompatActivity) context).getSupportFragmentManager();
                addEmp.setArguments(bundle);
                addEmp.show(fm, addEmp.getTag());

//                Dialog dialog;
//                dialog = new Dialog(context);
//                dialog.setContentView(R.layout.add_employee_dialog);
//                dialog.setCancelable(false);
//                EditText et1, et2, et3, et4;
//                et1 = dialog.findViewById(R.id.et1);
//                et2 = dialog.findViewById(R.id.et2);
//                et3 = dialog.findViewById(R.id.et3);
//                et4 = dialog.findViewById(R.id.et4);
//
//                et1.setText(singleUnit.getName());
//
//                et3.setText(singleUnit.getPhone());
//
//                Button saveBtn = dialog.findViewById(R.id.saveBtn);
//                saveBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        String s1, s2, s3, s4;
//                        s1 = et1.getText().toString();
//                        s2 = et2.getText().toString();
//                        s3 = et3.getText().toString();
//                        s4 = et4.getText().toString();
// for update
//
////                        ((MainActivity) context).addNewEmployeeFunc(s1, s2, s3, s4, singleUnit.getId());
//                        dialog.dismiss();
//                    }
//                });
//                dialog.show();

            }
        });

        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(context)
                        .setTitle("Do u really want to remove this employee ???")
                        .setPositiveButton("yes proceed",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {

                                        databaseHelper.employeeDao().deleteEmployee(new Employee(singleUnit.getId(), singleUnit.getName(), singleUnit.getFatherName(), singleUnit.getDob(), singleUnit.getGender(), singleUnit.getPhone(), singleUnit.getEmail(), singleUnit.getAddress(), singleUnit.getEmployeeId(), singleUnit.getDesignation(), singleUnit.getExperience(), singleUnit.isMaritalStatus(), singleUnit.getSalary()));
                                        ((MainActivity) context).showEmployees();
                                    }
                                }
                        )
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        dialog.dismiss();
                                    }
                                }
                        );
                b.show();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder {
        TextView name, phone, eId;
        CardView cardView;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardEmployee);
            name = itemView.findViewById(R.id.nameEt);
            phone = itemView.findViewById(R.id.phoneEt);
            eId = itemView.findViewById(R.id.eIdEt);
        }
    }
}
