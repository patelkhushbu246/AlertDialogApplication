package com.example.alertdialogapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    EditText uname_edt,pass_edt,cpass_edt;
    Button signupbtn;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        db = new DBHelper(this);
        uname_edt=findViewById(R.id.uname_edt_reg);
        pass_edt=findViewById(R.id.pwd_edt_reg);
        cpass_edt=findViewById(R.id.cpwd_edt_reg);
        signupbtn=findViewById(R.id.reg_btn);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = uname_edt.getText().toString().trim();
                String pwd = pass_edt.getText().toString().trim();
                String cpwd = cpass_edt.getText().toString().trim();
                if (pwd.equals(cpwd)) {
                    db.adduser(uname, pwd);

                       Toast.makeText(SignupActivity.this, "You have signup", Toast.LENGTH_SHORT).show();
                       Intent i = new Intent(SignupActivity.this, MainActivity.class);
                       startActivity(i);
                }else {
                    Toast.makeText(SignupActivity.this, " signup failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}