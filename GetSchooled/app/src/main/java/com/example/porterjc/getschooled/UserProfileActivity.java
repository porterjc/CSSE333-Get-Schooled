package com.example.porterjc.getschooled;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.ListView;


import java.sql.Connection;


public class UserProfileActivity extends Activity {
    private ProfileSchoolAdapter mSchoolAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        ServerConnectClass scc = new ServerConnectClass();
        Connection connection = scc.connect();
        mSchoolAdapter = new ProfileSchoolAdapter(this, R.layout.list_view_item_user_profile, connection);

        ListView test = (ListView)findViewById();

        test.setAdapter(mSchoolAdapter);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_profile, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
