package com.example.amuna;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class RegisterProfileActivity extends AppCompatActivity {

    private EditText et_nickname,et_birth,et_phoneNum,et_univ,et_jobEtc;
    private Button btn_registerPf1;
    private RadioGroup rg_korf;
    private Spinner sp_job;
    private int Nationality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_profile);

        et_nickname = findViewById(R.id.et_nickname);
        et_birth = findViewById(R.id.et_birth);
        et_phoneNum = findViewById(R.id.et_phoneNum);
        et_univ = findViewById(R.id.et_univ);
        et_jobEtc = findViewById(R.id.et_jobEtc);

        rg_korf = findViewById(R.id.rg_korf);

        btn_registerPf1 = findViewById(R.id.btn_registerPf1);

        sp_job = findViewById(R.id.sp_job);

        rg_korf.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_korean://내국인->0
                        Nationality = 0;
                        break;
                    case R.id.rb_foreigner://외국인->1
                        Nationality = 1;
                        break;
                }
            }
        });
    }
}
