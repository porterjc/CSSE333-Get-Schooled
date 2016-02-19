package com.example.porterjc.getschooled;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by porterjc on 2/19/2016.
 */
public class ClassAdapter extends BaseAdapter {
    protected Context mContext;
    private Connection mConnection;
    private ArrayList<SchoolObject> schoolInfo = new ArrayList<>();

    public ClassAdapter(Context context, Connection connection) {
        mConnection = connection;
        mContext = context;
        getSchoolData();
    }

    @Override
    public int getCount() {
        return schoolInfo.size();
    }

    @Override
    public Object getItem(int position) {
        return schoolInfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private void getSchoolData() {
        try {
            Statement statement = mConnection.createStatement();

            ResultSet schools = statement.executeQuery("SELECT [class_Name], [image]\n" +
                    "\tFROM [GetSchooledDataBase].[dbo].[Classes]\n" +
                    "\tWHERE [username] = '" + ServerConnectClass.getUser() + "';");

            while (schools.next()) {
                SchoolObject school = new SchoolObject(schools.getString(1), schools.getString(2));
                schoolInfo.add(school);
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
        View view = convertView;

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            view = inflater.inflate(R.layout.list_view_item_user_profile, null);
        }
        TextView schoolName = (TextView) view.findViewById(R.id.listViewSchoolName);
        ImageView schoolImage = (ImageView) view.findViewById(R.id.schoolProfilePictureListViewImageView);

        schoolName.setText(schoolInfo.get(position).mSchoolName);
//        schoolImage.set

        return view;
    }
}
