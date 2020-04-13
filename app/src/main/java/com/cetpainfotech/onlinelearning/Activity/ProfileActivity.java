package com.cetpainfotech.onlinelearning.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cetpainfotech.onlinelearning.R;

public class ProfileActivity extends AppCompatActivity {
SharedPreferences sp;
SharedPreferences.Editor ed;
EditText etname,etemail;
Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle("Update Profile");
        save = findViewById(R.id.btn_save);
        etemail = findViewById(R.id.etemail);
        etname = findViewById(R.id.etname);

        sp = getSharedPreferences("profile", 0);
        ed = sp.edit();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etemail.getText().toString().isEmpty() ||
                etname.getText().toString().isEmpty())
                {
                    Toast.makeText(ProfileActivity.this, "Enter Name and Email Id", Toast.LENGTH_SHORT).show();
                }
                else {
                    ed.putString("name", etname.getText().toString());
                    ed.putString("email", etemail.getText().toString());
                    ed.apply();
                    Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
