package com.example.porterjc.getschooled;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ClassesFacultyStudents extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes_faculty_students);

        Button classesButton = (Button) findViewById(R.id.classesButton);
        Button facultyButton = (Button) findViewById(R.id.facultyButton);
        Button studentsButton = (Button) findViewById(R.id.studentsButton);

        classesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchClassActivity();
            }
        });

        facultyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchFacultyActivity();
            }
        });

        studentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchStudentsActivity();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_classes_faculty_students, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //TODO

        //noinspection SimplifiableIfStatement
        if (id == R.id.about_app) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //TODO

    private void launchClassActivity() {

    }

    private void launchFacultyActivity() {

    }

    private void launchStudentsActivity() {

    }






}
