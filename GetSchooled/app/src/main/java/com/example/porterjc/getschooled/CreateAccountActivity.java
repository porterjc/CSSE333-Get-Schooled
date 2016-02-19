package com.example.porterjc.getschooled;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLClientInfoException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Types;

public class CreateAccountActivity extends Activity {
    ServerConnectClass mConnection;
    EditText mUsername;
    EditText mPassword;
    EditText email;
    Button mCreateAccountButton;
    EditText mProfilePicture;

    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);

        mConnection = new ServerConnectClass();
        mUsername = (EditText) findViewById(R.id.usernameEditText);
        mPassword = (EditText)findViewById(R.id.passwordEditText);
        email = (EditText)findViewById(R.id.emailEditText);
        mCreateAccountButton = (Button)findViewById(R.id.createAccountButton);
        mProfilePicture = (EditText)findViewById(R.id.profilePictureEditText);

        mCreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection con = mConnection.connect();
                if (con != null) {
                    createAcc(con);
                }
            }
        });
    }

    private void createAcc(Connection con) {
        try {
            CallableStatement statement = con.prepareCall("{? = call [dbo].[CreateAccount](?, ?, ?, ?)}");

            statement.registerOutParameter(1, Types.INTEGER);
            statement.setString(2, mUsername.getText().toString());
            statement.setString(3, mPassword.getText().toString());
            statement.setString(4, email.getText().toString());
            statement.setString(5,null);

            boolean rs = statement.execute();
            int valid = statement.getInt(1);

            statement.close();
            con.close();

            if (valid == 0) {
                System.out.println("A new account has probably been added to the DB. ");
                Intent intent = new Intent( this, UserProfileActivity.class ); // create the intent
                startActivity(intent); // start the activity
            } else {
                System.out.println("Something is wrong with the input.");
                //do things like say bad Password
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