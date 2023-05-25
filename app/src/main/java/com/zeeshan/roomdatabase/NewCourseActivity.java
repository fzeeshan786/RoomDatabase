package com.zeeshan.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewCourseActivity extends AppCompatActivity
{
    private EditText courseNameEdt,courseDescEdt,courseDurationEdt;
    private Button courseBtn;

    public static final String EXTRA_ID = "com.zeeshan.roomdatabase.EXTRA_ID";
    public static final String EXTRA_COURSE_NAME = "com.zeeshan.roomdatabase.EXTRA_COURSE_NAME";
    public static final String EXTRA_DESCRIPTION = "com.zeeshan.roomdatabase.EXTRA_DESCRIPTION";
    public static final String EXTRA_DURATION = "com.zeeshan.roomdatabase.EXTRA_DURATION";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_course);

        courseNameEdt = findViewById(R.id.idEditCourseName);
        courseDescEdt = findViewById(R.id.idEditCourseDescription);
        courseDurationEdt = findViewById(R.id.idEditCourseDuration);
        courseBtn = findViewById(R.id.idBtnSaveCourse);


        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID))
        {
            courseNameEdt.setText(intent.getStringExtra(EXTRA_COURSE_NAME));
            courseDescEdt.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            courseDurationEdt.setText(intent.getStringExtra(EXTRA_DURATION));
        }

        courseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String courseName = courseNameEdt.getText().toString();
                String courseDesc = courseDescEdt.getText().toString();
                String courseDuration = courseDurationEdt.getText().toString();
                if (courseName.isEmpty() || courseDesc.isEmpty() || courseDuration.isEmpty())
                {
                    Toast.makeText(NewCourseActivity.this, "Please enter the valid course details", Toast.LENGTH_SHORT).show();
                    return;
                }

                saveCourse(courseName, courseDesc, courseDuration);
            }
        });
    }
    private void saveCourse(String courseName, String courseDesc, String courseDuration)
    {
        Intent data = new Intent();
        data.putExtra(EXTRA_COURSE_NAME,courseName);
        data.putExtra(EXTRA_DESCRIPTION,courseDesc);
        data.putExtra(EXTRA_DURATION,courseDuration);
        int id = getIntent().getIntExtra(EXTRA_ID,-1);
        if (id != -1)
        {
            data.putExtra(EXTRA_ID,id);
        }
        setResult(RESULT_OK,data);
        Toast.makeText(this, "Course hase beeen saved to room database.", Toast.LENGTH_SHORT).show();
    }
}