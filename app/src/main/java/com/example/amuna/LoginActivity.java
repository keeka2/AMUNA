package com.example.amuna;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText et_email, et_pass;
    private Button btn_login;
    private TextView RegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_email = (EditText) findViewById(R.id.et_email);
        et_pass = (EditText) findViewById(R.id.et_pass);
        btn_login = (Button) findViewById(R.id.LoginButton);
        RegisterButton = (TextView) findViewById(R.id.btn_register1);

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterProfileActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
//
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = et_email.getText().toString();
                String Password = et_pass.getText().toString();

                PasswordEncryption passwordEncryption = new PasswordEncryption();
                //String Password = passwordEncryption.encrypt(et_pass.getText().toString());

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

                                Intent intent = new Intent(LoginActivity.this, LoginSplash.class);
                                intent.putExtra("Email", Email);
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
                LoginRequest loginRequest = new LoginRequest(Email,Password,responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }

}
