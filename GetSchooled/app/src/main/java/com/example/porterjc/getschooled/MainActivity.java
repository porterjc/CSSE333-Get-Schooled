package com.example.porterjc.getschooled;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.sql.CallableStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Types;

public class MainActivity extends Activity {
    ServerConnectClass connection;
    EditText username;
    EditText password;
    EditText email;
    Button loginB;
    Button createAccB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connection = new ServerConnectClass();
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        email = (EditText)findViewById(R.id.email);
        loginB = (Button) findViewById(R.id.login);
        createAccB = (Button) findViewById(R.id.newAccount);

        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection con = connection.connect();
                if (con != null) {
                    login(con);
                }
            }
        });
        createAccB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAcc();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.about_app) {
            DialogFragment dialogFragment = new DialogFragment() {
                @Override
                public Dialog onCreateDialog(Bundle savedInstanceState) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(
                            getActivity());
                    builder.setTitle(R.string.About);
                    builder.setMessage(R.string.About_Description);

                    builder.setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    dismiss();
                                }
                            });
                    return builder.create();
                }
            };
            dialogFragment.show(getFragmentManager(), null);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void login(Connection con) {
        try {
            CallableStatement statement = con.prepareCall("{? = call [dbo].[AccountLoginCheck](?, ?, ?)}");
            statement.registerOutParameter(1, Types.INTEGER);
            statement.setString(2, email.getText().toString());
            statement.setString(3, username.getText().toString());
            statement.setString(4, password.getText().toString());

            statement.execute();

            int valid = statement.getInt(1);

            statement.close();
            con.close();

            if (valid == 0) {
                ServerConnectClass.setUser(username.getText().toString());
                Intent intent = new Intent( this, UserProfileActivity.class ); // create the intent
                startActivity(intent); // start the activity
            } else {
                //do things like say bad mPassword
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

    void createAcc() {
        Intent intent = new Intent(this, CreateAccountActivity.class); // create the intent
        startActivity(intent); // start the activity
    }
}
