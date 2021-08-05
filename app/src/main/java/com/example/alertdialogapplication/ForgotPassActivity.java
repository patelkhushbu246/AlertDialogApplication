package com.example.alertdialogapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassActivity extends AppCompatActivity {

    EditText uname_edt,pass_edt,cpass_edt;
    Button resetbtn;
    DBHelper db;
    String uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        db = new DBHelper(this);
        uname_edt=findViewById(R.id.uname_edt_reset);
        pass_edt=findViewById(R.id.pwd_edt_reset);
        cpass_edt=findViewById(R.id.cpwd_edt_reset);
        resetbtn=findViewById(R.id.reset_btn);
        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder a=new AlertDialog.Builder(ForgotPassActivity.this);
                a.setMessage("Are you sure ?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        uname = uname_edt.getText().toString();
                        boolean res = db.checkuser(uname);
                        if (res == true) {
                            changepassword();
                        }
                    }
                }).setNegativeButton("No",null);
                AlertDialog c=a.create();
                c.show();

            }
        });
    }

    private void changepassword() {
        String pwd = pass_edt.getText().toString().trim();
        String cpwd = cpass_edt.getText().toString().trim();
        boolean res=db.updatepass(""+uname,pwd);
        if (res == true) {
            if (pwd.equals(cpwd)) {

                Toast.makeText(ForgotPassActivity.this, "Update Successfully...", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ForgotPassActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(ForgotPassActivity.this, "Password id not match", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(ForgotPassActivity.this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }
}