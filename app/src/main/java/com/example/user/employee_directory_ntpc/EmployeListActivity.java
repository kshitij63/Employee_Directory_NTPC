package com.example.user.employee_directory_ntpc;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class EmployeListActivity extends AppCompatActivity {
    ArrayList<Employe> employes;
    ArrayList<Employe> detail_em;
    Cursor cr;
    EmployeTestHelper helper;
    SQLiteDatabase db;
    String name,mobile,grade,department;
    ListView listView;
    String phone_message;
    String email;
    String rax_office;
    String rax_resident;
    String phone_office;
    String phone_resident;
    String qmr_num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employe_list);
        employes=new ArrayList<>();
        detail_em=new ArrayList<>();
        listView=(ListView) findViewById(R.id.listview);
        helper=new EmployeTestHelper(this);
        db=helper.getReadableDatabase();
        Bundle bundle=getIntent().getExtras();
        name=bundle.getString("name");
       // mobile=bundle.getString("mobile");
        grade=bundle.getString("grade");
        department=bundle.getString("department");
        Log.e("this",name+mobile+grade+department);
    //find_dummy_data();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(EmployeListActivity.this,DetailActivity.class);
                intent.putExtra("position",position);
                intent.putParcelableArrayListExtra("list",detail_em);
                startActivity(intent);
            }
        });
        getData();
    }

    private void find_dummy_data(){
cr=db.rawQuery("select * from employee where name='" +name +"' and mobile_call='" +mobile +"' and grade='" +grade +"' and department='" +department +"'",null);
if(cr.moveToFirst()){
    do
    {

        String photo=cr.getString(cr.getColumnIndex("photo"));
        email=cr.getString(cr.getColumnIndex("email"));
        phone_message=cr.getString(cr.getColumnIndex("mobile_sms"));

        //rax_office=cr.getString(cr.getColumnIndex("email"));
        //rax_resident=cr.getString(cr.getColumnIndex("email"));
        phone_office=cr.getString(cr.getColumnIndex("phone_o"));
        phone_resident=cr.getString(cr.getColumnIndex("phone_r"));
        rax_resident=cr.getString(cr.getColumnIndex("rax_r"));
        rax_office=cr.getString(cr.getColumnIndex("rax_o"));
        qmr_num=cr.getString(cr.getColumnIndex("qtr_num"));
        //db.execSQL("create table employee(_id integer primary key autoincrement,name text,grade " +
          //      "text,department text,photo text,mobile_call " +
            //    "text,mobile_sms text,email text,phone_r text,phone_o text,rax_o text,rax_r text,qtr_num text)");



        Employe employe=new Employe(photo,name,department,grade);employes.add(employe);
//    detail_em.add(employe1);
        //public Employe(String photo,String name,String department,String grade,String phone_call,String email,String
      //      phone_message,String phone_office,String phone_resident,String rax_office,String rax_resident,String qmr_num){

    }while (cr.moveToNext());
}

cr.close();



    }

    private void getData(){
        try {
            JSONArray array=new JSONArray(loadJSONFromAsset());

            for(int i=0;i<array.length();i++){
                JSONObject object=array.getJSONObject(i);
                if(object.has("EMP_ID")) {
                    Log.e("nope",object.getString("EMP_NAME") +object.getString("DEPT_ID"));
//                    Log.e("yes",object.getString("EMP_NAME") +object.getString("DEP_ID"));


                    if (Pattern.compile(Pattern.quote(name), Pattern.CASE_INSENSITIVE).matcher(object.getString("EMP_NAME").trim()).find() && grade.equals(object.getString("GRADE")) && department.equals(object.getString("DEPT_ID")) ) {
                        Log.e("yes",object.getString("EMP_NAME") +object.getString("DEPT_ID"));
                        Employe employe = new Employe("ghyy", object.getString("EMP_NAME"), object.getString("DEPT_ID"), object.getString("GRADE"));
                        Employe employe1=new Employe("ghyy",object.getString("EMP_NAME"),object.getString("DEPT_ID"),object.getString("GRADE"),object.getString("PHONE_MOB"),object.getString("EMAIL_ID"),object.getString("PHONE_MOB"),"N/A","N/A","N/A","N/A",object.getString("EMP_ID"));
                        detail_em.add(employe1);
                        employes.add(employe);
                        //name.equalsIgnoreCase(object.getString("EMP_NAME").trim())
                    }
                    listView.setAdapter(new EmployeAdapter(EmployeListActivity.this,employes));
                    Log.e("ru",employes.size()+"");
                    Log.e("ru",detail_em.size() +"");
                }

            }


        } catch (JSONException e) {
            Log.e("error",e.getMessage());
        }

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
