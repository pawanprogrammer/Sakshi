package com.cetpainfotech.onlinelearning.Authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cetpainfotech.onlinelearning.Activity.HomeActivity;
import com.cetpainfotech.onlinelearning.Activity.SplashActivity;
import com.cetpainfotech.onlinelearning.Chat.UserActivity;
import com.cetpainfotech.onlinelearning.Chat.UserDetails;
import com.cetpainfotech.onlinelearning.R;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    TextView tv_registerUser;
    EditText et_username, et_password;
    Button loginButton;
    String user, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        // typecasting of different elements
        tv_registerUser = (TextView) findViewById(R.id.register);
        et_username = (EditText) findViewById(R.id.username);
        et_password = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.loginButton);
        // click event on Register Button
        tv_registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        // click event on Login Button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Getting user & pass into string from editText
                user = et_username.getText().toString();
                pass = et_password.getText().toString();
                //Condition for user inputs
                if (user.equals("")) {
                    et_username.setError("can't be blank");
                } else if (pass.equals("")) {
                    et_password.setError("can't be blank");
                } else {
                    // url to get data from server
                    String url = "https://onlinelearning-eee65.firebaseio.com/users.json";
                    // progress dialog for user Authentication
                    final ProgressDialog pd = new ProgressDialog(LoginActivity.this);
                    pd.setMessage("Loading...");
                    pd.show();
                    // Get user data from server
                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            if (s.equals("null")) {
                                Toast.makeText(LoginActivity.this, "user not found", Toast.LENGTH_LONG).show();
                            }
                            else {
                                try {
                                    JSONObject obj = new JSONObject(s);
                                    if (!obj.has(user)) {
                                        Toast.makeText(LoginActivity.this, "user not found", Toast.LENGTH_LONG).show();
                                    }
                                    /*checking user and pass from server
                                    * put user and pass into userDetails class
                                    * */
                                    else if (obj.getJSONObject(user).getString("password").equals(pass)) {
                                        UserDetails.username = user;
                                        UserDetails.password = pass;
                                        startActivity(new Intent(LoginActivity.this, SplashActivity.class));
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(LoginActivity.this, "incorrect password", Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            // dismiss progress dialog
                            pd.dismiss();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            System.out.println("" + volleyError);
                            pd.dismiss();
                        }
                    });
                    RequestQueue rQueue = Volley.newRequestQueue(LoginActivity.this);
                    rQueue.add(request);
                }
            }
        });
    }
}
