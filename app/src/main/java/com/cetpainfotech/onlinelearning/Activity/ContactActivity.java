package com.cetpainfotech.onlinelearning.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cetpainfotech.onlinelearning.Authentication.RegisterActivity;
import com.cetpainfotech.onlinelearning.R;

import java.util.HashMap;
import java.util.Map;

public class ContactActivity extends AppCompatActivity {
Button btn_submit;
EditText etname,etemail,etmobile,etmsg;
String sname,semail,smobile,smsg;
    String Url = "http://searchkero.com/onlinelearn/contact.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        getSupportActionBar().setTitle("Contact Us");
        btn_submit = findViewById(R.id.btn_submit);
        etname = findViewById(R.id.etname);
        etemail = findViewById(R.id.etemail);
        etmobile = findViewById(R.id.etmobile);
        etmsg = findViewById(R.id.etmsg);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sname = etname.getText().toString();
                semail = etemail.getText().toString();
                smobile = etmobile.getText().toString();
                smsg = etmsg.getText().toString();
//              security features for user input
                if (sname.equals("")) {
                    etname.setError("can't be blank");
                } else if (semail.equals("")) {
                    etemail.setError("can't be blank");
                } else if (smobile.equals("")) {
                    etmobile.setError("can't be blank");
                } else if (smsg.equals("")) {
                    etmsg.setError("can't be blank");
                } else if (smobile.length() > 10) {
                    etmobile.setError("at least 10 number long");
                } else {
                    final ProgressDialog pd = new ProgressDialog(ContactActivity.this);
                    pd.setMessage("Loading...");
                    pd.show();

                    StringRequest sr = new StringRequest(1, Url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            pd.dismiss();
                            etemail.setText("");
                            etmobile.setText("");
                            etmsg.setText("");
                            etname.setText("");
                            Toast.makeText(ContactActivity.this, response, Toast.LENGTH_SHORT).show();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            pd.dismiss();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> map = new HashMap<>();
                            map.put("name", sname);
                            map.put("email", semail);
                            map.put("mobile", smobile);
                            map.put("msg", smsg);
                            return map;
                        }
                    };
                    RequestQueue rq = Volley.newRequestQueue(ContactActivity.this);
                    rq.add(sr);
                }
            }

        });



    }
}
