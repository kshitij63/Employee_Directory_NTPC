package com.example.user.employee_directory_ntpc;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class EmployeListActivity extends AppCompatActivity {
    ArrayList<Employe> employes;
    Cursor cr;
    EmployeTestHelper helper;
    SQLiteDatabase db;
    String name,mobile,grade,department;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employe_list);
        employes=new ArrayList<>();
        listView=(ListView) findViewById(R.id.listview);
        helper=new EmployeTestHelper(this);
        db=helper.getReadableDatabase();
        Bundle bundle=getIntent().getExtras();
        name=bundle.getString("name");
        mobile=bundle.getString("mobile");
        grade=bundle.getString("grade");
        department=bundle.getString("department");
        Log.e("this",name+mobile+grade+department);
    find_dummy_data();
    }

    private void find_dummy_data(){
cr=db.rawQuery("select * from employee where name='" +name +"' and mobile_call='" +mobile +"' and grade='" +grade +"' and department='" +department +"'",null);
if(cr.moveToFirst()){
    do
    {

        String photo=cr.getString(cr.getColumnIndex("photo"));
        Employe employe=new Employe(photo,name,department,grade);employes.add(employe);

    }while (cr.moveToNext());
    listView.setAdapter(new EmployeAdapter(EmployeListActivity.this,employes));
}

cr.close();



    }
}
