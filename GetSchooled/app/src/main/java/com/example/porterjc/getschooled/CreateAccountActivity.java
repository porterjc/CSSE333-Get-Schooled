package com.example.porterjc.getschooled;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLClientInfoException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by porterjc on 2/11/2016.
 */
public class CreateAccountActivity extends AppCompatActivity{
    ServerConnectClass connection;
    EditText username;
    EditText password;
    EditText email;
    Button createAccB;
    Button setPicB;
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);
        connection = new ServerConnectClass();
        username = (EditText) findViewById(R.id.usernameEditText);
        password = (EditText)findViewById(R.id.passwordEditText);
        email = (EditText)findViewById(R.id.emailEditText);
        //profPic = (EditText)findViewById(R.id.pictureEditText);
        createAccB = (Button)findViewById(R.id.createAccountButton);
        setPicB = (Button)findViewById(R.id.profilePictureButton);

        createAccB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection con = connection.connect();
                if (con != null) {
                    createAcc(con);
                }
            }
        });

        setPicB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void createAcc(Connection con) {
        try {
            CallableStatement statement = con.prepareCall("{? = call [dbo].[CreateAccount](?, ?, ?, ?)}");
            statement.registerOutParameter(1, Types.INTEGER);
            statement.setString(2, username.getText().toString());
            statement.setString(3, password.getText().toString());
            statement.setString(4, email.getText().toString());
            statement.setString(5, "");
            boolean rs = statement.execute();

            int valid = statement.getInt(1);

            statement.close();
            con.close();

            if (valid == 0) {
                System.out.println("A new account has probably been added to the DB. ");
//                Intent intent = new Intent( this, ProfileSchoolListAdapter.class ); // create the intent
//                startActivity(intent); // start the activity
            } else {
                System.out.println("Something is wrong with the input.");
                //do things like say bad password
            }

        }
        catch (SQLException mSQLException) {
            if(mSQLException instanceof SQLClientInfoException){
                mSQLException.printStackTrace();
                //some toast message to user.
            }else if(mSQLException instanceof SQLDataException) {
                mSQLException.printStackTrace();
                //some toast message to user.
            }else{
                mSQLException.printStackTrace();
            }
        }
    }
}
