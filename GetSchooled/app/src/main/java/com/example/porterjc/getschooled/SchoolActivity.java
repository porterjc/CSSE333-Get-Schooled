package com.example.porterjc.getschooled;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SchoolActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);

        Button enterButton = (Button) findViewById(R.id.enterSchoolButton);
        ImageView schoolImage = (ImageView) findViewById(R.id.schoolImageView);
        ImageView school = (ImageView) findViewById(R.id.schoolProfilePictureListViewImageView);
        schoolImage.setImageDrawable(school.getDrawable());

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launch();
            }
        });


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

        //noinspection SimplifiableIfStatement
        if (id == R.id.about_app) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void launch() {
        Intent classesFacultyStudents = new Intent(this, ClassesFacultyStudents.class);
        startActivity(classesFacultyStudents);
    }


}
