package com.example.user.employee_directory_ntpc;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import static android.R.attr.button;

public class MainActivity extends AppCompatActivity {
Spinner spinner,spinner_grade;
    Button bt;
    EditText name,mobile;
    String grade,department;
    String [] departments,grades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt=(Button) findViewById(R.id.button);
        grades=new String[100];
        departments=new String[100];
        name=(EditText) findViewById(R.id.edit_text_name);
        mobile=(EditText) findViewById(R.id.edit_text_mobile);
        spinner=(Spinner) findViewById(R.id.spinner_dept);
        spinner_grade=(Spinner) findViewById(R.id.spinner_grade);
        grades=getResources().getStringArray(R.array.grade);
        departments=getResources().getStringArray(R.array.dept);
        ArrayAdapter<CharSequence> gradeAdapter=ArrayAdapter.createFromResource(this,R.array.grade,R.layout.support_simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> departmentAdapter=ArrayAdapter.createFromResource(this,R.array.dept,R.layout.support_simple_spinner_dropdown_item);
        departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gradeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(departmentAdapter);
        spinner_grade.setAdapter(gradeAdapter);
        put_dummy_data();
        put_dummy_data();
        put_dummy_data();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                department=departments[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                grade=grades[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equals("")|| mobile.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"Empty Field Not Allowed",Toast.LENGTH_SHORT).show();

                }
                else {
                    Intent intent = new Intent(MainActivity.this, EmployeListActivity.class);
                    intent.putExtra("name", name.getText().toString());
                    intent.putExtra("mobile", mobile.getText().toString());
                    intent.putExtra("grade", grade);
                    intent.putExtra("department", department);
                    startActivity(intent);
                }

            }
        });

    }

    private void put_dummy_data(){
        EmployeTestHelper helper=new EmployeTestHelper(this);
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name","Kshitij");
        values.put("grade","Mercury");
        values.put("department","Uranus");
        values.put("photo","http://www.youthconnect.in/wp-content/uploads/2014/11/Shahrukh_Khan.jpg");
        values.put("mobile_call","9953319602");
        values.put("mobile_sms","9953319602");
        values.put("email","kshitij6325@gmail.com");
        values.put("phone_o","NA");
        values.put("phone_r","NA");
        values.put("rax_o","NA");
        values.put("rax_r","NA");
        values.put("qtr_num","6657");
        db.insert("employee",null,values);


    }
}
