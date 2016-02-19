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

import org.w3c.dom.Text;

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

        //TODO: OnItemClickListener

        mSchoolAdapter = new ProfileSchoolAdapter(this, connection);
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

        if (id == R.id.create_new_school) {
            newSchoolDialog();
            return true;
        } else if (id == R.id.change_password) {
            changePasswordDialog();
        } else if (id == R.id.change_profile_picture) {
            changeProfilePicture();
        }


        //TODO

        return super.onOptionsItemSelected(item);
    }

    private void newSchoolDialog() {
        DialogFragment dialogFragment = new DialogFragment() {
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                // Inflate View
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_add, null);
                builder.setView(view);

                EditText newSchoolNameEditText = (EditText) view.findViewById(R.id.dialogEditText);
                final String newSchoolName = newSchoolNameEditText.getText().toString();

                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });

                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

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
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                // Inflate View
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_change_password, null);
                builder.setView(view);

                EditText usernameEditText = (EditText) view.findViewById(R.id.changePasswordUsername);
                EditText oldPasswordEditText = (EditText) view.findViewById(R.id.changePasswordOldPassword);
                EditText newPasswordEditText = (EditText) view.findViewById(R.id.changePasswordNewPassword);

                final String  username = usernameEditText.getText().toString();
                final  String oldPassword = oldPasswordEditText.getText().toString();
                final String newPassword = newPasswordEditText.getText().toString();

                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });

                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //TODO: Check Username & Password with Database and then change mPassword in Database

                                dismiss();
                            }
                        });
                return builder.create();
            }
        };
        dialogFragment.show(getFragmentManager(), null);
    }

    private void changeProfilePicture() {
        DialogFragment dialogFragment = new DialogFragment() {
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        getActivity());
                // Inflate View
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_add, null);
                builder.setView(view);

                TextView textView = (TextView) view.findViewById(R.id.dialogTextView);
                textView.setText(R.string.Change_User_Profile_Image);

                EditText newProfilePictureEditText = (EditText) view.findViewById(R.id.dialogEditText);
                final String newProfilePicture = newProfilePictureEditText.getText().toString();

                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });

                builder.setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //TODO: Add Profile Picture to Database

                                mSchoolAdapter.notifyDataSetChanged();
                                dismiss();
                            }
                        });
                return builder.create();
            }
        };
        dialogFragment.show(getFragmentManager(), null);
    }





}
