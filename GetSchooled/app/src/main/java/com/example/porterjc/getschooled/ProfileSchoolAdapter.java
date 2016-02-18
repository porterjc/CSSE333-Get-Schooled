package com.example.porterjc.getschooled;


import android.app.Activity;
import android.content.Context;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.ImageView;
import android.widget.TextView;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;


public class ProfileSchoolAdapter extends ArrayAdapter<SchoolObject> {
    Context context;
    int layoutResourceId;
    ArrayList<SchoolObject> schools = null;
    TextView mSchoolNameTextView;
    ImageView mSchoolImageView;
    int mNoPicture = 0;

    public ProfileSchoolAdapter(Context context, int layoutResourceId, ArrayList<SchoolObject> schools) {
        super(context, layoutResourceId, schools);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.schools = schools;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            mSchoolNameTextView = (TextView) row.findViewById(R.id.listViewSchoolName);
            mSchoolImageView = (ImageView) row.findViewById(R.id.schoolProfilePictureListViewImageView);
        }
        SchoolObject school = schools.get(position);
        mSchoolNameTextView.setText(school.mSchoolName);

        if(school.mSchoolPictureAddress == mNoPicture) {
            mSchoolImageView.setImageResource(R.drawable.no_image_available);
        }
        else {
            mSchoolImageView.setImageResource(school.mSchoolPictureAddress);
        }
        return row;
    }


    public SchoolObject getItem(int position) {
        return schools.get(position);
    }

    //    public ProfileSchoolAdapter(Context context, int resource, Connection connection) {
//        super(context,  resource);
//        mConnection = connection;
//        getSchoolData();
//    }
//
//    private void getSchoolData() {
//        try {
//            CallableStatement statement = mConnection.prepareCall("{call [dbo].[GetSchoolsForUser](?, ?, ?)}");
//            statement.registerOutParameter(1, Types.VARCHAR);
//            statement.registerOutParameter(2, Types.VARCHAR);
//            statement.setString(3, ServerConnectClass.getUser());
//            statement.execute();
//            ResultSet schools = statement.getResultSet();
//            statement.close();
//            mConnection.close();
//
//            while (schools.next()) {
//                System.out.println(schools.getString(1));
//                //schoolInfo.add(schools.getString(1));
////                schoolInfo.add(schools.getString(2));
//            }
//
//
//        } catch (SQLException mSQLException) {
//            if (mSQLException instanceof SQLClientInfoException) {
//                mSQLException.printStackTrace();
//                //some toast message to user.
//            } else if (mSQLException instanceof SQLDataException) {
//                mSQLException.printStackTrace();
//                //some toast message to user.
//            } else {
//                mSQLException.printStackTrace();
//            }
//        }
//    }
}