package com.example.porterjc.getschooled;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Connection;

public class UserProfileActivity extends Activity {
    ProfileSchoolAdapter mSchoolAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        TextView usernameText = (TextView) findViewById(R.id.profileUsername);
        usernameText.setText(ServerConnectClass.getUser());

        ServerConnectClass scc = new ServerConnectClass();
        Connection connection = scc.connect();

        ListView test = (ListView) findViewById(R.id.listView);

        mSchoolAdapter = new ProfileSchoolAdapter(this, connection);

        test.setAdapter(mSchoolAdapter);

        //        mSchools = new ArrayList<>();

//        mSchools.add(new SchoolObject("Rose-Hulman", R.drawable.rose_hulman));
//        mSchools.add(new SchoolObject("Mount Saint Mary Academy", R.drawable.mount_saint_mary_academy1));

//        ProfileSchoolAdapter schoolAdapter = new ProfileSchoolAdapter(this, R.layout.list_view_item_user_profile, mSchools);
//        test.setAdapter(schoolAdapter);

//        mSchoolAdapter = new ProfileSchoolAdapter(this, R.layout.list_view_item_user_profile, connection);
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

        if (id == R.id.create_new_school) {
            newSchoolDialog();
            return true;
        } else if (id == R.id.change_password) {
            changePasswordDialog();
        }


        //TODO

        return super.onOptionsItemSelected(item);
    }

    private void newSchoolDialog() {
        DialogFragment dialogFragment = new DialogFragment() {
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        getActivity());
                // Inflate View
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_add, null);
                builder.setView(view);

                EditText newSchoolNameEditText = (EditText) view.findViewById(R.id.dialogEditText);
                final String newSchoolName = newSchoolNameEditText.getText().toString();

                builder.setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                SchoolObject newSchool = new SchoolObject(newSchoolName, null);

                                //TODO: Add to Database

                                mSchoolAdapter.notifyDataSetChanged();
                                dismiss();
                            }
                        });
                return builder.create();
            }
        };
        dialogFragment.show(getFragmentManager(), null);
    }

    private void changePasswordDialog() {
        DialogFragment dialogFragment = new DialogFragment() {
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        getActivity());
                // Inflate View
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_change_password, null);
                builder.setView(view);

                builder.setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {

                                //TODO: Add to Database

                                dismiss();
                            }
                        });
                return builder.create();
            }
        };
        dialogFragment.show(getFragmentManager(), null);
    }
}
