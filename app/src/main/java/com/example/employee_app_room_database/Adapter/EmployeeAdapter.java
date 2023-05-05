package com.example.employee_app_room_database.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

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
        holder.age.setText("age- " + String.valueOf(singleUnit.getAge()));
        holder.phone.setText("phone- " + singleUnit.getPhone());
        holder.weight.setText("weight- " + String.valueOf(singleUnit.getWeight()));
        holder.eId.setText("employee Id- " + String.valueOf(singleUnit.getId()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog;
                dialog = new Dialog(context);
                dialog.setContentView(R.layout.add_employee_dialog);
                dialog.setCancelable(false);
                EditText et1, et2, et3, et4;
                et1 = dialog.findViewById(R.id.et1);
                et2 = dialog.findViewById(R.id.et2);
                et3 = dialog.findViewById(R.id.et3);
                et4 = dialog.findViewById(R.id.et4);

                et1.setText(singleUnit.getName());
                et2.setText(String.valueOf(singleUnit.getAge()));
                et3.setText(singleUnit.getPhone());
                et4.setText(String.valueOf(singleUnit.getWeight()));
                Button saveBtn = dialog.findViewById(R.id.saveBtn);
                saveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s1, s2, s3, s4;
                        s1 = et1.getText().toString();
                        s2 = et2.getText().toString();
                        s3 = et3.getText().toString();
                        s4 = et4.getText().toString();

                        ((MainActivity) context).addNewEmployeeFunc(s1, s2, s3, s4, singleUnit.getId());
                        dialog.dismiss();
                    }
                });
                dialog.show();

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
                                        databaseHelper.employeeDao().deleteEmployee(new Employee(singleUnit.getId(), singleUnit.getName(), singleUnit.getAge(), singleUnit.getPhone(), singleUnit.getWeight()));
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
        TextView name, age, phone, weight, eId;
        CardView cardView;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardEmployee);
            name = itemView.findViewById(R.id.nameEt);
            age = itemView.findViewById(R.id.ageEt);
            phone = itemView.findViewById(R.id.phoneEt);
            weight = itemView.findViewById(R.id.weightEt);
            eId = itemView.findViewById(R.id.eIdEt);
        }
    }
}
