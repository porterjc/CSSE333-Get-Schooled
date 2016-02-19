package com.example.porterjc.getschooled;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserProfileActivity extends Activity {
    ProfileSchoolAdapter mSchoolAdapter;
    Connection mConnection;
    private int logOutNumber = 72;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        ServerConnectClass scc = new ServerConnectClass();
        mConnection = scc.connect();

        TextView usernameText = (TextView) findViewById(R.id.profileUsername);
        usernameText.setText(ServerConnectClass.getUser());

        ImageView userProfilePic = (ImageView) findViewById(R.id.userProfilePicture);

        try {
            Statement statement = mConnection.createStatement();
            ResultSet image = statement.executeQuery("SELECT [profile_picture]\n" +
                    "\tFROM [GetSchooledDatabase].[dbo].[Account]\n" +
                    "\tWHERE [username] = 'test';");

            while (image.next()) {
                String imageLink = image.getString(1);

                Bitmap bitmap = null;
                if (imageLink != null) {
                    //Modified From: http://stackoverflow.com/questions/13969526/converting-image-from-url-to-bitmap

                    URL url;
                    try {
                        url = new URL(imageLink);
                        bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    userProfilePic.setImageBitmap(bitmap);
                } else {
                    //Do nothing
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ListView schoolList = (ListView) findViewById(R.id.listView);

        mSchoolAdapter = new ProfileSchoolAdapter(this, mConnection);
        schoolList.setAdapter(mSchoolAdapter);

        schoolList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SchoolObject selectedSchool = mSchoolAdapter.getItem(position);

                Intent schoolIntent = new Intent(getBaseContext(), SchoolActivity.class);

                schoolIntent.putExtra("KEY_SCHOOL_NAME", selectedSchool.mSchoolName);
                schoolIntent.putExtra("KEY_SCHOOL_IMAGE", selectedSchool.mSchoolPictureAddress);
                schoolIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getBaseContext().startActivity(schoolIntent);
            }
        });
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
            return true;
        } else if (id == R.id.change_profile_picture) {
            changeProfilePicture();
            return true;
        } else if (id == R.id.deleteAccount) {
            deleteAccountDialog();
            return true;
        } else if (id == R.id.logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void newSchoolDialog() {
        DialogFragment dialogFragment = new DialogFragment() {
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                // Inflate View
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_create_school, null);
                builder.setView(view);

                final EditText newSchoolNameEditText = (EditText) view.findViewById(R.id.createNewSchoolName);
                final EditText newSchoolPrincipalNameEditText = (EditText) view.findViewById(R.id.createNewSchoolPrincipal);

                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });

                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newSchoolName = newSchoolNameEditText.getText().toString();
                        String newPrincipalName = newSchoolPrincipalNameEditText.getText().toString();
                        try {
                            CallableStatement statement = mConnection.prepareCall("{call [dbo].[CreateSchool](?, ?, ?)}");
                            statement.setString(1, newSchoolName);
                            statement.setString(2, newPrincipalName);
                            statement.setString(3, ServerConnectClass.getUser());
//
                            statement.executeUpdate();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        mSchoolAdapter.notifyDataSetChanged();
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
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

                final EditText usernameEditText = (EditText) view.findViewById(R.id.changePasswordUsername);
                final EditText oldPasswordEditText = (EditText) view.findViewById(R.id.changePasswordOldPassword);
                final EditText newPasswordEditText = (EditText) view.findViewById(R.id.changePasswordNewPassword);

                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });

                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String username = usernameEditText.getText().toString();
                        String oldPassword = oldPasswordEditText.getText().toString();
                        String newPassword = newPasswordEditText.getText().toString();

                        try {
                            CallableStatement statement = mConnection.prepareCall("{call [dbo].[ChangePassword](?, ?, ?)}");
                            statement.setString(1, username);
                            statement.setString(2, oldPassword);
                            statement.setString(3, newPassword);

                            statement.executeUpdate();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
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
                View view = inflater.inflate(R.layout.dialog_change_profile_picture, null);
                builder.setView(view);

                final EditText newProfilePictureEditText = (EditText) view.findViewById(R.id.dialogEditText);
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
                                String newProfilePicture = newProfilePictureEditText.getText().toString();

                                try {
                                    CallableStatement statement = mConnection.prepareCall("{call [dbo].[ChangeProfilePic](?, ?)}");
                                    statement.setString(1, ServerConnectClass.getUser().toString());
                                    statement.setString(2, newProfilePicture);

                                    statement.executeUpdate();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                                dismiss();
                            }
                        });
                return builder.create();
            }
        };
        dialogFragment.show(getFragmentManager(), null);
    }

    private void deleteAccountDialog() {
        DialogFragment dialogFragment = new DialogFragment() {
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                // Inflate View
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_delete_account, null);
                builder.setView(view);

                final EditText usernameEditText = (EditText) view.findViewById(R.id.deleteAccountUsernameEditText);

                final EditText emailEditText = (EditText) view.findViewById(R.id.deleteAccountEmailEditText);

                Button deleteAccountButton = (Button) view.findViewById(R.id.deleteAccountButton);

                deleteAccountButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String username = usernameEditText.getText().toString();
                        String email = emailEditText.getText().toString();

                        if (username != ServerConnectClass.getUser()) {
                            dismiss();
                        }

                        try {
                            CallableStatement statement = mConnection.prepareCall("{call [dbo].[DeleteAccount](?, ?)}");
                            statement.setString(1, username);
                            statement.setString(2, email);

                            statement.executeUpdate();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        finish();
                        dismiss();
                    }
                });

                return builder.create();
            }
        };
        dialogFragment.show(getFragmentManager(), null);
    }

    private void logout() {
        DialogFragment dialogFragment = new DialogFragment() {
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.Logout);
                builder.setMessage(R.string.Logout_Message);

                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });

                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO: LogOut
                        ServerConnectClass.setUser(null);
                        Intent intent = new Intent (getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        dismiss();
                        finish();
                        setResult(72);
                    }
                });
                return builder.create();
            }
        };
        dialogFragment.show(getFragmentManager(), null);
    }
}
