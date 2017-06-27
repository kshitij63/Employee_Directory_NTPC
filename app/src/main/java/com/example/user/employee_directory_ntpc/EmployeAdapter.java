package com.example.user.employee_directory_ntpc;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by user on 6/27/2017.
 */

public class EmployeAdapter extends ArrayAdapter<Employe> {
    Context context;
    ArrayList<Employe> employes;
    public EmployeAdapter(Context context,ArrayList<Employe> employes) {
        super(context, R.layout.employee);
        this.context=context;
        this.employes=employes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.employee,parent,false);
        }
        ImageView imageView=(ImageView) convertView.findViewById(R.id.photo);
        TextView textView=(TextView) convertView.findViewById(R.id.name);
        TextView textView1=(TextView) convertView.findViewById(R.id.grade);
        TextView textView2=(TextView) convertView.findViewById(R.id.department);
        Picasso.with(context).load(employes.get(position).getPhoto()).into(imageView);
        textView.setText(employes.get(position).getName());
        textView2.setText(employes.get(position).getDepartment());
        textView1.setText(employes.get(position).getGrade());

return convertView;
    }

    @Override
    public int getCount() {
        return employes.size();
    }
}
