package com.example.amuna;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.CountDownTimer;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;

public class RegisterActivity extends AppCompatActivity{

    private EditText et_pass,et_repass;
    private TextView et_email;
    private Button btn_register1;
    private RadioGroup rg_gender;
    private String Gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        /////////////////////////

        Intent authIntent = getIntent();
        String auth_email = authIntent.getStringExtra("Email");
        et_email = (TextView) findViewById(R.id.et_email);
        et_email.setText(auth_email);
        et_pass = findViewById(R.id.et_pass);
        et_repass = findViewById(R.id.et_repass);
        rg_gender = findViewById(R.id.rg_gender);

        rg_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_gender_m:
                        Gender = "남성";
                        break;
                    case R.id.rb_gender_w:
                        Gender = "여성";
                        break;
                }
            }
        });

        btn_register1 = findViewById(R.id.btn_register1);

        btn_register1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Email = et_email.getText().toString();
                final String Password = et_pass.getText().toString();
                final String RePassword = et_repass.getText().toString();


                if(!Password.equals(RePassword)){
                    Toast.makeText(RegisterActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    et_pass.setText("");
                    et_repass.setText("");
                    et_pass.requestFocus();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                Log.e("확인","Email:"+Email+"/Password:"+Password+"/Gender:"+Gender);
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("회원가입완료")
                                        .setPositiveButton("확인",null)
                                        .create()
                                        .show();

                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            }
                            else
                            {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                    builder.setMessage("회원가입실패")
                                            .setNegativeButton("다시시도",null)
                                            .create()
                                            .show();
                            }
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }

                };
                    RegisterRequest registerRequest = new RegisterRequest(Email, Password, Gender, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                    queue.add(registerRequest);
                }
        });
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

//


}