package com.example.porterjc.getschooled;


import android.content.Context;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

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
    private Connection mConnection = null;
    private ArrayList<String> schoolInfo = new ArrayList<>();

    public ProfileSchoolAdapter(Context context, int resource, Connection connection) {
        super(context,  resource);
        mConnection = connection;
        getSchoolData();
    }

    private void getSchoolData() {
        try {
            CallableStatement statement = mConnection.prepareCall("{call [dbo].[GetSchoolsForUser](?, ?, ?)}");
            statement.registerOutParameter(1, Types.VARCHAR);
            statement.registerOutParameter(2, Types.VARCHAR);
            statement.setString(3, ServerConnectClass.getUser());
            statement.execute();
            ResultSet schools = statement.getResultSet();
            statement.close();
            mConnection.close();

            while (schools.next()) {
                System.out.println(schools.getString(1));
                //schoolInfo.add(schools.getString(1));
//                schoolInfo.add(schools.getString(2));
            }


        } catch (SQLException mSQLException) {
            if (mSQLException instanceof SQLClientInfoException) {
                mSQLException.printStackTrace();
                //some toast message to user.
            } else if (mSQLException instanceof SQLDataException) {
                mSQLException.printStackTrace();
                //some toast message to user.
            } else {
                mSQLException.printStackTrace();
            }
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //ArrayAdapter.getView() takes care of recycling the views (if it can)
        View view =  super.getView(position, convertView, parent);

        TextView schoolName = (TextView) convertView.findViewById(R.id.listViewSchoolName);
//        ImageView schoolImage = (ImageView) convertView.findViewById(R.id.schoolProfilePictureListViewImageView);

        schoolName.setText(schoolInfo.get(position));
//        schoolName.setTextColor(R.color.textColor);
        return view;
    }
}