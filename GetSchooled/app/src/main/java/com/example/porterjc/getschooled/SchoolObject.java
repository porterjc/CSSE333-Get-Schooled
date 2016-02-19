package com.example.porterjc.getschooled;

/**
 * Created by schmidkl on 2/9/2016.
 */
public class SchoolObject {

    public String mSchoolName;
    public String mSchoolPictureAddress;

    public SchoolObject() {
        super();
    }

    public SchoolObject(String schoolName, String pictureAddress) {
        mSchoolName = schoolName;
        mSchoolPictureAddress = pictureAddress;
    }

    //TODO: Add Getters & Setters

    public String getSchoolName() {
        return mSchoolName;
    }

}
