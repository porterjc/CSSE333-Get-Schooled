package com.example.porterjc.getschooled;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
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

import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    ServerConnectClass connection;
    EditText username;
    EditText password;
    Button loginB;
    Button createAccB;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connection = new ServerConnectClass();
        username= (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        loginB = (Button)findViewById(R.id.login);
        createAccB = (Button)findViewById(R.id.newAccount);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
                Connection con = connection.connect();
                if (con != null) {
                    username.setText("It worked!");
                    String query = "";
                    //Statement stmt = con.createStatement();
                    //ResultSet rs = stmt.executeQuery(query);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
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
            //username.setText(con.getCatalog());
            String query = "EXEC AccountLoginCheck '" + username + "','" + password + "'";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setEscapeProcessing(true);
            stmt.setQueryTimeout(5);
            ResultSet rs = stmt.executeQuery();
            int valid;
            //if (rs.next()) {
                valid = rs.getInt(0);
                username.setText("Yes");
            //}
        }
        catch (SQLException mSQLException) {
            if(mSQLException instanceof SQLClientInfoException){
                //some toast message to user.
            }else if(mSQLException instanceof SQLDataException) {
                //some toast message to user.
            }else{

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
