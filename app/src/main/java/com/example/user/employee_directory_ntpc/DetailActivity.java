package com.example.user.employee_directory_ntpc;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
ArrayList<Employe> employes;
    int position;
    Employe employe;
    TextView name,grade,deapartment,phone_call,message,phone_o,phoe_res,rax_o,rax_r,email,qtr_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle bundle=getIntent().getExtras();
        employes=bundle.getParcelableArrayList("list");
        position=bundle.getInt("position");
        employe=employes.get(position);
        if(employes.get(position).getPhoto()=="hujj"){
        Picasso.with(this).load(R.drawable.na).into((ImageView) findViewById(R.id.image_de));
        }
        else {
            ((ImageView) findViewById(R.id.image_de)).setImageBitmap(BitmapFactory.decodeByteArray(Base64.decode(employes.get(position).getPhoto(),0),0,Base64.decode(employes.get(position).getPhoto(),0).length));

        }
        name=(TextView) findViewById(R.id.name_de);
        grade=(TextView) findViewById(R.id.garde_de);
        deapartment=(TextView) findViewById(R.id.dep_de);
        phone_call=(TextView) findViewById(R.id.mob_call_de);
        message=(TextView) findViewById(R.id.message_de);
        phone_o=(TextView) findViewById(R.id.office_call_de);
        phoe_res=(TextView) findViewById(R.id.resident_call_de);
        rax_o=(TextView) findViewById(R.id.rax_office_de);
        rax_r=(TextView) findViewById(R.id.rax_resident__de);
        qtr_num=(TextView) findViewById(R.id.qtr_de);
        email=(TextView) findViewById(R.id.mail_de);

        name.setText(employe.getName());
        grade.setText(employe.getGrade());
        deapartment.setText(employe.getDepartment());
        phone_call.setText(employe.getPhone_call());
        message.setText(employe.getPhone_message());
        phone_o.setText(employe.getPhone_office());
        phoe_res.setText(employe.getPhone_resident());
        rax_r.setText(employe.getRax_resident());
        rax_o.setText(employe.getRax_office());
        qtr_num.setText(employe.getQmr_num());
        email.setText(employe.getEmail());

        phone_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" +employe.getPhone_call()));
                startActivity(intent);
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("sms:" +employe.getPhone_message()));
                startActivity(intent);
            }
        });

    }
}
