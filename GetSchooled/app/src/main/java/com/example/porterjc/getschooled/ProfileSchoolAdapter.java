package com.example.porterjc.getschooled;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProfileSchoolAdapter extends BaseAdapter {
    protected Context mContext;
    private Connection mConnection;
    private ArrayList<SchoolObject> schoolInfo = new ArrayList<>();

    public ProfileSchoolAdapter(Context context, Connection connection) {
        mConnection = connection;
        mContext = context;
        getSchoolData();
    }

    @Override
    public int getCount() {
        return schoolInfo.size();
    }

    @Override
    public SchoolObject getItem(int position) {
        return schoolInfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private void getSchoolData() {
        try {
            Statement statement = mConnection.createStatement();

            ResultSet schools = statement.executeQuery("SELECT [school_name], [school_image]\n" +
                    "\tFROM [GetSchooledDatabase].[dbo].[School]\n" +
                    "\tWHERE [username] = '" + ServerConnectClass.getUser() + "';");

            while (schools.next()) {
                String schoolName = schools.getString(1);
                String schoolImageLink =  schools.getString(2);

                SchoolObject school = new SchoolObject(schoolName, schoolImageLink);
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

        if (schoolInfo.get(position).mSchoolPictureAddress != null) {
            //Modified From: http://stackoverflow.com/questions/13969526/converting-image-from-url-to-bitmap
            Bitmap bitmap = null;
            URL url;
            try {
                url = new URL(schoolInfo.get(position).mSchoolPictureAddress);
                bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            schoolImage.setImageBitmap(bitmap);
        }
        return view;
    }
}