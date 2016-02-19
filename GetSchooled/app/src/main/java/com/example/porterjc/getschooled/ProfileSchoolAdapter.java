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


public class ProfileSchoolAdapter extends BaseAdapter {
    private Context mContext;
    private Connection mConnection;
    private ArrayList<SchoolObject> schoolInfo = new ArrayList<>();
    private LayoutInflater mLayoutInflater = null;

    public ProfileSchoolAdapter(Context context, Connection connection) {
        mConnection = connection;
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
//            statement.registerOutParameter(1, Types.VARCHAR);
//            statement.registerOutParameter(2, Types.VARCHAR);
//            statement.setString(3, ServerConnectClass.getUser());

            ResultSet schools = statement.executeQuery("SELECT [school_name], [school_image]\n" +
                    "\tFROM [GetSchooledDataBase].[dbo].[School]\n" +
                    "\tWHERE [username] = '" + ServerConnectClass.getUser() + "';");
            //statement.close();
            //mConnection.close();

            while (schools.next()) {
//                System.out.println(schools.getString(1));

                SchoolObject school = new SchoolObject(schools.getString(1), schools.getString(2));
                System.out.println(school.getSchoolName());

                schoolInfo.add(school);
//                schoolInfo.add(schools.getString(1));
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
        View view = convertView;

        if (convertView == null) {
            view = mLayoutInflater.inflate(R.layout.list_view_item_user_profile, null);
        }
        TextView schoolName = (TextView) view.findViewById(R.id.listViewSchoolName);
        ImageView schoolImage = (ImageView) view.findViewById(R.id.schoolProfilePictureListViewImageView);

        schoolName.setText(schoolInfo.get(position).mSchoolName);
//        schoolImage.set

        return view;
    }


//    public ProfileSchoolAdapter(Context context, int layoutResourceId, ArrayList<SchoolObject> schools) {
//        super(context, layoutResourceId, schools);
//        this.layoutResourceId = layoutResourceId;
//        this.context = context;
//        this.schools = schools;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View row = convertView;
//        if(row == null)
//        {
//            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
//            row = inflater.inflate(layoutResourceId, parent, false);
//            mSchoolNameTextView = (TextView) row.findViewById(R.id.listViewSchoolName);
//            mSchoolImageView = (ImageView) row.findViewById(R.id.schoolProfilePictureListViewImageView);
//        }
//        SchoolObject school = schools.get(position);
//        mSchoolNameTextView.setText(school.mSchoolName);
//        mSchoolImageView.setImageResource(school.mSchoolPictureAddress);
//        return row;
//    }


//    public ProfileSchoolAdapter(Context context, int resource, Connection connection) {
//        super(context,  resource);
//        mConnection = connection;
//        getSchoolData();
//    }


}