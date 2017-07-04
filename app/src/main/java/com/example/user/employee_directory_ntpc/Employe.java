package com.example.user.employee_directory_ntpc;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 6/27/2017.
 */

public class Employe implements  Parcelable {
    String photo;
    String name;
    String department;
    String grade;
    String phone_call;
    String phone_message;
    String email;
    String rax_office;
    String rax_resident;
    String phone_office;
    String phone_resident;
    String qmr_num;

    public Employe(String photo,String name,String department,String grade,String phone_call,String email,String phone_message,String phone_office,String phone_resident,String rax_office,String rax_resident,String qmr_num){
        this.photo=photo;
        this.name=name;
        this.department=department;
        this.grade=grade;
        this.phone_call=phone_call;
        this.email=email;
        this.phone_message=phone_message;
        this.phone_office=phone_office;
        this.phone_resident=phone_resident;
        this.rax_office=rax_office;
        this.rax_resident=rax_resident;
        this.qmr_num=qmr_num;

    }

    public static final Creator<Employe> CREATOR = new Creator<Employe>() {
        @Override
        public Employe createFromParcel(Parcel in) {
            return new Employe(in);
        }

        @Override
        public Employe[] newArray(int size) {
            return new Employe[size];
        }
    };

    public String getEmail() {
        return email;
    }

    public String getPhone_call() {
        return phone_call;
    }

    public String getPhone_message() {
        return phone_message;
    }

    public String getQmr_num() {
        return qmr_num;
    }

    public String getRax_office() {
        return rax_office;
    }

    public String getRax_resident() {
        return rax_resident;
    }

    public String getPhone_office() {
        return phone_office;
    }

    public String getPhone_resident() {
        return phone_resident;
    }

    public Employe(String photo, String name, String department, String grade){
        this.photo=photo;
        this.name=name;
        this.department=department;
        this.grade=grade;
    }

    protected Employe(Parcel in) {
        photo = in.readString();
        name = in.readString();
        department = in.readString();
        grade = in.readString();
        phone_call=in.readString();
        phone_message=in.readString();
        email=in.readString();
        rax_office=in.readString();
        rax_resident=in.readString();
        phone_office=in.readString();
        phone_resident=in.readString();
        qmr_num=in.readString();

    }



    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getGrade() {
        return grade;
    }

    public String getPhoto() {
        return photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(photo);
        dest.writeString(name);
        dest.writeString(department);
        dest.writeString(grade);
        dest.writeString(phone_call);
        dest.writeString(phone_message);
        dest.writeString(email);
        dest.writeString(rax_office);
        dest.writeString(rax_resident);
        dest.writeString(phone_office);
        dest.writeString(phone_resident);
        dest.writeString(qmr_num);
    }
}
