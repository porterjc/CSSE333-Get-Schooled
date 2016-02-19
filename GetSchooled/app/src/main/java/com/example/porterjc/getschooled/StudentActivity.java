package com.example.porterjc.getschooled;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Connection;

/**
 * Created by porterjc on 2/19/2016.
 */
public class StudentActivity extends Activity {

    private ProfileSchoolAdapter mClassAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        TextView usernameText = (TextView) findViewById(R.id.profileUsername);
        usernameText.setText(ServerConnectClass.getUser());

        ServerConnectClass scc = new ServerConnectClass();
        Connection connection = scc.connect();

        ListView test = (ListView) findViewById(R.id.listView);

        //TODO: OnItemClickListener

        mClassAdapter = new ProfileSchoolAdapter(this, connection);
        test.setAdapter(mClassAdapter);
    }
}
