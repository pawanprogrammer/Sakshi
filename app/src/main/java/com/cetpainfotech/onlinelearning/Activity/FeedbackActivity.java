package com.cetpainfotech.onlinelearning.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cetpainfotech.onlinelearning.R;

public class FeedbackActivity extends AppCompatActivity {
    Button btn_feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        btn_feedback = findViewById(R.id.btn_email);
        getSupportActionBar().setTitle("Feedback");

        btn_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{ "Sakshigandhi8219@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "");
                intent.putExtra(Intent.EXTRA_TEXT, "");
                //need this to prompts email client only
                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent, "Choose an Email:"));
            }
        });
    }
}
