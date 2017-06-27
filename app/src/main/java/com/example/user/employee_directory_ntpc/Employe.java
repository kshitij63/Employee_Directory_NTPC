package com.example.user.employee_directory_ntpc;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 6/27/2017.
 */

public class Employe implements Parcelable {
    String photo;
    String name;
    String department;
    String grade;

    public Employe(String photo,String name,String department,String grade){
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
    }
}
