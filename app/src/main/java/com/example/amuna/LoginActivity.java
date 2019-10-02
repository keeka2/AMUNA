package com.example.amuna;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText Email = (EditText) findViewById(R.id.et_email);
        final EditText Password = (EditText) findViewById(R.id.et_pass);
        final Button LoginButton = (Button) findViewById(R.id.LoginButton);
        final TextView RegisterButton = (TextView) findViewById(R.id.btn_register1);
//
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*메일인증
                Intent authIntent = new Intent(LoginActivity.this, Auth.class);
                LoginActivity.this.startActivity(authIntent);
                */
                Intent authIntent = new Intent(LoginActivity.this, Auth.class);
                LoginActivity.this.startActivity(authIntent);
            }
        });
//
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userEmail = Email.getText().toString();
                final String userPassword = Password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){

                                String Email = jsonResponse.getString("Email");
                                String Password = jsonResponse.getString("Password");
                                Intent intent = new Intent(LoginActivity.this, Main2Activity.class);
                                intent.putExtra("Email", Email);
                                intent.putExtra("Password",Password);
                                LoginActivity.this.startActivity(intent);
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("로그인에 실패하였습니다")
                                        .setNegativeButton("다시 시도",null)
                                        .create()
                                        .show();
                            }

                        } catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userEmail,userPassword,responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }

}
