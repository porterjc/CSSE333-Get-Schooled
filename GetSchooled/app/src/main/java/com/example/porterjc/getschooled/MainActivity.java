package com.example.porterjc.getschooled;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;

public class MainActivity extends AppCompatActivity {
    ServerConnectClass connection;
    EditText username;
    EditText password;
    Button loginB;
    Button createAccB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connection = new ServerConnectClass();
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginB = (Button) findViewById(R.id.login);
        createAccB = (Button) findViewById(R.id.newAccount);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

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
                creatAcc();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    void creatAcc() {
        Intent intent = new Intent(this, CreateAccountActivity.class); // create the intent
        startActivity(intent); // start the activity
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void login(Connection con) {
        try {
            CallableStatement statement = con.prepareCall("{? = call [dbo].[AccountLoginCheck](?, ?)}");
            statement.registerOutParameter(1, Types.INTEGER);
            statement.setString(2, username.getText().toString());
            statement.setString(3, password.getText().toString());
            boolean rs = statement.execute();

            int valid = statement.getInt(1);

            statement.close();
            con.close();

            if (valid == 0) {
//                Intent intent = new Intent( this, ProfileSchoolListAdapter.class ); // create the intent
//                startActivity(intent); // start the activity
            } else {
                //do things like say bad password
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

//        DialogFragment dialogFragment = new DialogFragment() {
//            @Override
//            public Dialog onCreateDialog(Bundle savedInstanceState) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(
//                        getActivity());
//                // Inflate View
//                LayoutInflater inflater = getActivity().getLayoutInflater();
//                View view = inflater.inflate(R.layout.top_student, null);
//                builder.setView(view);
//                return builder.create();
//            }
//        };
//        dialogFragment.show(getFragmentManager(), null);
    }
}
