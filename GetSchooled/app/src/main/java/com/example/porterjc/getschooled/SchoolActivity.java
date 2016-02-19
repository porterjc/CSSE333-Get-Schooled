package com.example.porterjc.getschooled;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SchoolActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);

        Intent intent = getIntent();

        String schoolNameIntentData = intent.getStringExtra("KEY_SCHOOL_NAME");
        String schoolImageIntentData = intent.getStringExtra("KEY_SCHOOL_IMAGE");

        TextView schoolNameTextView = (TextView) findViewById(R.id.schoolNameSchoolActivity);
        ImageView schoolImage = (ImageView) findViewById(R.id.schoolImageView);

        schoolNameTextView.setText(schoolNameIntentData);
//        schoolImage.setImage

        Button enterButton = (Button) findViewById(R.id.enterSchoolButton);

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launch();
            }
        });
    }

    private void launch() {
        Intent classesFacultyStudents = new Intent(this, ClassesFacultyStudents.class);
        startActivity(classesFacultyStudents);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_school, menu);

        //TODO

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //TODO

        if (id == R.id.edit_school_name) {
            editSchoolNameDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void editSchoolNameDialog() {
        DialogFragment dialogFragment = new DialogFragment() {
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        getActivity());
                // Inflate View
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_change_profile_picture, null);
                builder.setView(view);

                TextView editSchoolNameTextView = (TextView) view.findViewById(R.id.dialogTextView);

                editSchoolNameTextView.setText(R.string.Edit_Your_School_Name);

                EditText newSchoolNameEditText = (EditText) view.findViewById(R.id.dialogEditText);
                final String editedSchoolName = newSchoolNameEditText.getText().toString();


                builder.setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
//                                SchoolObject newSchool = new SchoolObject(newSchoolName, null);

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
