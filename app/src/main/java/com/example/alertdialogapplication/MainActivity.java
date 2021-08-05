package com.example.alertdialogapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText unameedt,passedt;
    TextView forgotpassbtn;
    Button loginbtn,signupbtn;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this);
        unameedt=findViewById(R.id.uname_edt);
        passedt=findViewById(R.id.pwd_edt);
        forgotpassbtn=findViewById(R.id.forgetpwd_btn);
        loginbtn=findViewById(R.id.login_btn);
        signupbtn=findViewById(R.id.signup_btn);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,SignupActivity.class);
                startActivity(i);
            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user=unameedt.getText().toString();
                String pass=passedt.getText().toString();
                boolean res=db.checklogin(user,pass);
                if (res == true){
                    Toast.makeText(MainActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        forgotpassbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder a=new AlertDialog.Builder(MainActivity.this);
                a.setMessage("Want to Change Password ?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Intent intent = new Intent(MainActivity.this, ForgotPassActivity.class);
                        startActivity(intent);
                    }
                }).setNegativeButton("No",null);
                AlertDialog c=a.create();
                c.show();

            }
        });
    }
}