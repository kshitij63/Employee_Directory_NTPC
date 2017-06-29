package com.example.user.employee_directory_ntpc;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static android.R.attr.button;

public class MainActivity extends AppCompatActivity {
Spinner spinner,spinner_grade;
    Button bt;
           // ArrayList<String> mecu;
    EditText name;
    String grade,department;
    String [] departments,grades;
ArrayAdapter<CharSequence> departmentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt=(Button) findViewById(R.id.button);
        grades=new String[100];
        spinner=(Spinner) findViewById(R.id.spinner_dept);

            gradest();
        // catch (JSONException e) {
          //  Log.e("erroe",e.getMessage());
        //}
//        for(int i=0;i<mecu.size();i++){
  //          Log.e("we",mecu.get(i));
    //    }
        name=(EditText) findViewById(R.id.edit_text_name);
        //mobile=(EditText) findViewById(R.id.edit_text_mobile);
        spinner_grade=(Spinner) findViewById(R.id.spinner_grade);
        grades=getResources().getStringArray(R.array.grade);
        //departments=getResources().getStringArray(R.array.dept);
        ArrayAdapter<CharSequence> gradeAdapter=ArrayAdapter.createFromResource(this,R.array.grade,R.layout.support_simple_spinner_dropdown_item);
        gradeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_grade.setAdapter(gradeAdapter);

        //put_dummy_data();
        //put_dummy_data();
        //put_dummy_data();

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
                if(name.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"Empty Field Not Allowed",Toast.LENGTH_SHORT).show();

                }
                else {
                    Intent intent = new Intent(MainActivity.this, EmployeListActivity.class);
                    intent.putExtra("name", name.getText().toString());
                    //intent.putExtra("mobile", mobile.getText().toString());
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

    private  void gradest()  {


        //ArrayList<String> grades=new ArrayList<>();
        //ArrayList<String> we=new ArrayList<>();
        JSONArray array= null;

        try {
            Set<String> hs=new HashSet<>();

            array = new JSONArray(loadJSONFromAsset());
            departments = new String[array.length()];
            departmentAdapter = new ArrayAdapter<CharSequence>(this, R.layout.support_simple_spinner_dropdown_item);

            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                if(object.has("DEPT_ID")) {
                    hs.add(object.getString("DEPT_ID")) ;
                }
                else {
                    hs.add("N/A");
                }

//                departments[i] = dept_id;
                //departmentAdapter.add(departments[i]);

            //    Log.e("yeaaaah", departments[i]);

            }
            departments=hs.toArray(new String[hs.size()]);
for(int i=0;i<departments.length;i++){
    departmentAdapter.add(departments[i]);
}

            departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner.setAdapter(departmentAdapter);


        }catch (JSONException e) {
            Log.e("error",e.getMessage());
        }

        //hs.addAll(grades);
        //grades.clear();
        //departments=  hs.toArray(new String[hs.size()]);
  //      if(departments[3].equals("")){
          //  Log.e("ka",hs.size() +"");
//        }

        //for(int i=0;i<grades.size();i++){
          //  departments[i]=grades.get(i);

//Log.e("called",departments[i]);
  //      }
       // for(int i=0;i<departments.length;i++){
         //   Log.e("clear",departments[i]);
       // }


    }
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("codebeautify (1).json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}
