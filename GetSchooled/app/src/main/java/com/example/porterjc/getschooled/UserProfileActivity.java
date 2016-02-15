package com.example.porterjc.getschooled;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Connection;


public class UserProfileActivity extends AppCompatActivity {
    private ServerConnectClass connection;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        connection = new ServerConnectClass();
//        username = (EditText) findViewById(R.id.usernameEditText);
//        password = (EditText)findViewById(R.id.passwordEditText);
//        email = (EditText)findViewById(R.id.emailEditText);
//        //profPic = (EditText)findViewById(R.id.pictureEditText);
//        createAccB = (Button)findViewById(R.id.createAccountButton);
//        setPicB = (Button)findViewById(R.id.profilePictureButton);

//        createAccB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Connection con = connection.connect();
//                if (con != null) {
//                    createAcc(con);
//                }
//            }
//        });

//        setPicB.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
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
