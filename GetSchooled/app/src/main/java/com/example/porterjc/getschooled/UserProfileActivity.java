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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;


public class UserProfileActivity extends Activity implements AdapterView.OnItemClickListener {
    ArrayList<SchoolObject> mSchools;
    int mNoPicture = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        TextView usernameText = (TextView) findViewById(R.id.profileUsername);

        usernameText.setText(ServerConnectClass.getUser());


//        ServerConnectClass scc = new ServerConnectClass();
//        Connection connection = scc.connect();

        ListView schoolList = (ListView) findViewById(R.id.listView);

        mSchools = new ArrayList<>();

        mSchools.add(new SchoolObject("Rose-Hulman", R.drawable.rose_hulman));
        mSchools.add(new SchoolObject("Mount Saint Mary Academy", R.drawable.mount_saint_mary_academyI));

        ProfileSchoolAdapter schoolAdapter = new ProfileSchoolAdapter(this, R.layout.list_view_item_user_profile, mSchools);
        schoolList.setAdapter(schoolAdapter);



//        mSchoolAdapter = new ProfileSchoolAdapter(this, R.layout.list_view_item_user_profile, connection);
//        test.setAdapter(mSchoolAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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


        switch (id) {
            case (R.id.createNewSchool):
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

                        builder.setPositiveButton(android.R.string.ok,
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        SchoolObject newSchool = new SchoolObject(newSchoolName, mNoPicture);
                                        dismiss();
                                    }
                                });

                        builder.setNegativeButton(android.R.string.ok,
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


        //TODO

        return super.onOptionsItemSelected(item);
    }
}
