package com.sqlitesample1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mrunal3006 on 24-Jul-18.
 */

public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.MyViewHolder>  {
    private Context context;
private List<EmployeeModel> employeeModelList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView empNameTxt,ageTxt,designationTxt,joinDateTxt;

        public MyViewHolder(View view) {
            super(view);
            empNameTxt = (TextView) view.findViewById(R.id.empNameTxt);
            ageTxt = (TextView) view.findViewById(R.id.ageTxt);
            designationTxt = (TextView) view.findViewById(R.id.designationTxt);
            joinDateTxt = (TextView) view.findViewById(R.id.joinDateTxt);
        }
    }


    @Override
    public EmployeeListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new EmployeeListAdapter.MyViewHolder(itemView);

    }


    public EmployeeListAdapter(List<EmployeeModel> employeeModels) {
        this.employeeModelList = employeeModels;
    }


    @Override
    public void onBindViewHolder(EmployeeListAdapter.MyViewHolder holder, int position) {

        EmployeeModel employeeModel=employeeModelList.get(position);

        holder.empNameTxt.setText(employeeModel.getName());
        holder.ageTxt.setText(context.getString(R.string.lbl_age1)+ employeeModel.getAge()+context.getString(R.string.lbl_age2));
        holder.designationTxt.setText(employeeModel.getDesignation());
        holder.joinDateTxt.setText(employeeModel.getJoindate());
    }

    @Override
    public int getItemCount() {
        return employeeModelList.size();
    }
}
